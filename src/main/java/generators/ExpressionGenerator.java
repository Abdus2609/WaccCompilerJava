package generators;

import static generators.ExpressionType.BOOL_LITERAL;
import static generators.ExpressionType.CHAR_LITERAL;

import antlr.BasicParser.ArrayElemContext;
import antlr.BasicParser.ExprContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import types.Array;
import types.Type;
import visitors.InterpretVisitor;
import visitors.State;

public class ExpressionGenerator implements Generator {

  private final State state;
  private ExpressionType exprType;
  private OperatorType operatorType;

  private Generator lhsInnerGenerator;
  private Generator rhsInnerGenerator;

  private Type op1Type;
  private Type op2Type;
  private String operand1;
  private String operand2;
  private boolean op1IsIdent = false;
  private boolean op2IsIdent = false;
  private boolean op1IsBracket = false;
  private boolean op2IsBracket = false;

  private ExpressionGenerator lhsBracketGen;
  private ExpressionGenerator rhsBracketGen;

  private InterpretVisitor visitor;
  private ArrayElemContext tree;

  public ExpressionGenerator(State state) {
    this.state = state;
  }

  public void setExprType(ExpressionType exprType) {
    this.exprType = exprType;
  }

  public void setOperatorType(OperatorType operatorType) {
    this.operatorType = operatorType;
  }

  public void setOperand1(String operand1) {
    this.operand1 = operand1;
  }

  public void setOperand2(String operand2) {
    this.operand2 = operand2;
  }

  public void setOp1Type(Type op1Type) {
    this.op1Type = op1Type;
  }

  public void setOp2Type(Type op2Type) {
    this.op2Type = op2Type;
  }

  public void setOp1IsIdent(boolean op1IsIdent) {
    this.op1IsIdent = op1IsIdent;
  }

  public void setOp2IsIdent(boolean op2IsIdent) {
    this.op2IsIdent = op2IsIdent;
  }

  public void setOp1IsBracket(boolean op1IsBracket) {
    this.op1IsBracket = op1IsBracket;
  }

  public void setOp2IsBracket(boolean op2IsBracket) {
    this.op2IsBracket = op2IsBracket;
  }

  public void setLhsBracketGen(ExpressionGenerator lhsBracketGen) {
    this.lhsBracketGen = lhsBracketGen;
  }

  public void setRhsBracketGen(ExpressionGenerator rhsBracketGen) {
    this.rhsBracketGen = rhsBracketGen;
  }

  public void setLhsInnerGenerator(Generator generator) {
    this.lhsInnerGenerator = generator;
  }

  public void setRhsInnerGenerator(Generator rhsInnerGenerator) {
    this.rhsInnerGenerator = rhsInnerGenerator;
  }

  public void setVisitor(InterpretVisitor visitor) {
    this.visitor = visitor;
  }

  public void setTree(ArrayElemContext tree) {
    this.tree = tree;
  }

  private void loadUnOpReg(
      List<Instruction> instructions,
      Optional<String> availReg,
      boolean isBoolean,
      boolean isChar,
      boolean onlyIdent) {
    if (op1IsIdent) {
      instructions.addAll(
          new ExprIdentGenerator(state, operand1, availReg, isBoolean, isChar).generate());
    } else {
      if (!onlyIdent) {
        instructions.addAll(
            new ExprLiteralGenerator(state, operand1, availReg, isBoolean, isChar).generate());
      }
    }
  }

  private String loadBinOpRegs(
      List<Instruction> instructions,
      Optional<String> availReg,
      boolean isBoolean,
      boolean isChar) {

    if (operand1 == null) {
      instructions.addAll(lhsInnerGenerator.generate());
    } else {
      if (op1IsBracket) {
        instructions.addAll(lhsBracketGen.generate());
        state.freeRegister(state.getResultRegister());
      } else {
        if (op1IsIdent) {
          instructions.addAll(
              new ExprIdentGenerator(state, operand1, availReg, isBoolean, isChar).generate());
        } else {
          instructions.addAll(
              new ExprLiteralGenerator(state, operand1, availReg, isBoolean, isChar).generate());
        }
      }
    }

    String reg1 = state.getResultRegister();
    availReg = state.getAvailableRegister();

    if (operand2 == null) {
      instructions.addAll(rhsInnerGenerator.generate());
    } else {
      if (op2IsBracket) {
        instructions.addAll(rhsBracketGen.generate());
        state.freeRegister(state.getResultRegister());
      } else {
        if (op2IsIdent) {
          instructions.addAll(
              new ExprIdentGenerator(state, operand2, availReg, isBoolean, isChar).generate());
        } else {
          instructions.addAll(
              new ExprLiteralGenerator(state, operand2, availReg, isBoolean, isChar).generate());
        }
      }
    }

    return reg1;
  }

  public List<Instruction> generate() {

    List<Instruction> instructions = new ArrayList<>();

    Optional<String> availReg = state.getAvailableRegister();

    switch (exprType) {
      case INT_LITERAL:
      case SIGNED_INT_LITERAL:
      case BOOL_LITERAL:
      case CHAR_LITERAL:
      case PAIR_LITERAL:
        instructions.addAll(
            new ExprLiteralGenerator(
                    state, operand1, availReg, exprType == BOOL_LITERAL, exprType == CHAR_LITERAL)
                .generate());
        break;
      case ARRAY_ELEMENT:
        int offset = state.getAddress(operand1);
        String reg;
        if (availReg.isPresent()) {
          reg = availReg.get();
        } else {
          reg = "r10";
          instructions.add(new Instruction("PUSH {" + reg + "}"));
          state.freeRegister(reg);
        }

        state.allocRegister(reg);
        instructions.add(new Instruction("ADD " + reg + ", sp, #" + offset));

        if (!tree.expr().isEmpty()) {
          for (int i = 0; i < tree.expr().size() - 1; i++) {
            ExprContext expr = tree.expr().get(i);
            instructions.addAll(visitor.visitExpr(expr).generate());
            instructions.add(new Instruction("LDR " + reg + ", [" + reg + "]"));
            instructions.add(new Instruction("MOV r0, " + state.getResultRegister()));
            instructions.add(new Instruction("MOV r1, " + reg));
            instructions.add(new Instruction("BL p_check_array_bounds"));
            state.addErrorFunction("p_check_array_bounds");
            instructions.add(new Instruction("ADD " + reg + ", " + reg + ", #4"));
            instructions.add(
                new Instruction(
                    "ADD " + reg + ", " + reg + ", " + state.getResultRegister() + ", LSL #2"));
            state.freeRegister(state.getResultRegister());
          }

          Type arrType = ((Array) state.getType(tree.IDENT().getText())).getBaseArrayType();

          ExprContext finalExpr = tree.expr().get(tree.expr().size() - 1);
          instructions.addAll(visitor.visitExpr(finalExpr).generate());
          instructions.add(new Instruction("LDR " + reg + ", [" + reg + "]"));
          instructions.add(new Instruction("MOV r0, " + state.getResultRegister()));
          instructions.add(new Instruction("MOV r1, " + reg));
          instructions.add(new Instruction("BL p_check_array_bounds"));
          state.addErrorFunction("p_check_array_bounds");
          instructions.add(new Instruction("ADD " + reg + ", " + reg + ", #4"));
          if (arrType.toString().equals("char")) {
            instructions.add(
                new Instruction("ADD " + reg + ", " + reg + ", " + state.getResultRegister()));
          } else {
            instructions.add(
                new Instruction(
                    "ADD " + reg + ", " + reg + ", " + state.getResultRegister() + ", LSL #2"));
          }
        }

        state.freeRegister(state.getResultRegister());
        instructions.add(new Instruction("LDR " + reg + ", [" + reg + "]"));
        state.setResultRegister(reg);

        break;
      case STRING_LITERAL:
        instructions.addAll(
            new ExprLiteralGenerator(state, operand1, availReg, false, false).generate());
        break;
      case IDENTIFIER:
        Type typ = state.getType(operand1);
        boolean isBool = typ.toString().equals("bool");
        boolean isChr = typ.toString().equals("char");
        instructions.addAll(
            new ExprIdentGenerator(state, operand1, availReg, isBool, isChr).generate());
        break;
      case UNOP_EXPR:
        switch (operatorType) {
          case NOT:
            loadUnOpReg(instructions, availReg, true, false, false);
            String usedReg = state.getResultRegister();
            instructions.add(new Instruction("EOR " + usedReg + ", " + usedReg + ", #1"));
            break;
          case BITWISE_NOT:
            loadUnOpReg(instructions, availReg, false, false, false);
            usedReg = state.getResultRegister();
            String maskReg = state.getAvailableRegister().get();
            instructions.add(new Instruction("LDR " + maskReg + ", =4294967295"));
            instructions.add(new Instruction("EOR " + usedReg + ", " + usedReg + ", " + maskReg));
            state.freeRegister(maskReg);
            break;
          case UNARY_MINUS:
            loadUnOpReg(instructions, availReg, false, false, true);
            usedReg = state.getResultRegister();
            instructions.add(new Instruction("RSBS " + usedReg + ", " + usedReg + ", #0"));
            instructions.add(new Instruction("BLVS p_throw_overflow_error"));
            state.addErrorFunction("p_throw_overflow_error");
            break;
          case LEN:
            loadUnOpReg(instructions, availReg, false, false, true);
            usedReg = state.getResultRegister();
            instructions.add(new Instruction("LDR " + usedReg + ", [" + usedReg + "]"));
            break;
          case ORD:
            loadUnOpReg(instructions, availReg, false, true, false);
            break;
          case CHR:
            loadUnOpReg(instructions, availReg, false, false, false);
            break;
        }
        break;

      case BINOP_EXPR:
        switch (operatorType) {
          case PLUS:
            String reg1 = loadBinOpRegs(instructions, availReg, false, false);

            String reg2 = state.getResultRegister();
            if (state.getPushCounter() > 0) {
              instructions.add(new Instruction("POP {r11}"));
              reg2 = "r11";
              state.minusPush();
            }
            state.freeRegister(reg2);

            instructions.add(new Instruction("ADDS " + reg1 + ", " + reg1 + ", " + reg2));
            instructions.add(new Instruction("BLVS p_throw_overflow_error"));
            state.addErrorFunction("p_throw_overflow_error");
            state.setResultRegister(reg1);
            break;
          case BINARY_MINUS:
            reg1 = loadBinOpRegs(instructions, availReg, false, false);

            reg2 = state.getResultRegister();
            if (state.getPushCounter() > 0) {
              instructions.add(new Instruction("POP {r11}"));
              reg2 = "r11";
              state.minusPush();
            }
            state.freeRegister(reg2);

            instructions.add(new Instruction("SUBS " + reg1 + ", " + reg1 + ", " + reg2));
            instructions.add(new Instruction("BLVS p_throw_overflow_error"));
            state.addErrorFunction("p_throw_overflow_error");
            state.setResultRegister(reg1);
            break;
          case MULT:
            reg1 = loadBinOpRegs(instructions, availReg, false, false);

            reg2 = state.getResultRegister();
            if (state.getPushCounter() > 0) {
              instructions.add(new Instruction("POP {r11}"));
              reg2 = "r11";
              state.minusPush();
            }
            state.freeRegister(reg2);

            instructions.add(
                new Instruction("SMULL " + reg1 + ", " + reg2 + ", " + reg1 + ", " + reg2));
            instructions.add(new Instruction("CMP " + reg2 + ", " + reg1 + ", ASR #31"));
            instructions.add(new Instruction("BLNE p_throw_overflow_error"));
            state.addErrorFunction("p_throw_overflow_error");
            state.setResultRegister(reg1);
            break;
          case MOD:
            reg1 = loadBinOpRegs(instructions, availReg, false, false);

            reg2 = state.getResultRegister();
            if (state.getPushCounter() > 0) {
              instructions.add(new Instruction("POP {r11}"));
              reg2 = "r11";
              state.minusPush();
            }
            state.freeRegister(reg2);

            instructions.add(new Instruction("MOV r0, " + reg1));
            instructions.add(new Instruction("MOV r1, " + reg2));
            instructions.add(new Instruction("BL p_check_divide_by_zero"));
            state.addErrorFunction("p_check_divide_by_zero");
            instructions.add(new Instruction("BL __aeabi_idivmod"));
            instructions.add(new Instruction("MOV " + reg1 + ", r1"));
            state.setResultRegister(reg1);
            break;
          case DIVIDE:
            reg1 = loadBinOpRegs(instructions, availReg, false, false);

            reg2 = state.getResultRegister();
            if (state.getPushCounter() > 0) {
              instructions.add(new Instruction("POP {r11}"));
              reg2 = "r11";
              state.minusPush();
            }
            state.freeRegister(reg2);

            instructions.add(new Instruction("MOV r0, " + reg1));
            instructions.add(new Instruction("MOV r1, " + reg2));
            instructions.add(new Instruction("BL p_check_divide_by_zero"));
            state.addErrorFunction("p_check_divide_by_zero");
            instructions.add(new Instruction("BL __aeabi_idiv"));
            instructions.add(new Instruction("MOV " + reg1 + ", r0"));
            state.setResultRegister(reg1);
            break;
          case GREATER_THAN:
            checkOp1Type();
            reg1 = loadBinOpRegs(instructions, availReg, false, op1Type.toString().equals("char"));

            reg2 = state.getResultRegister();
            if (state.getPushCounter() > 0) {
              instructions.add(new Instruction("POP {r11}"));
              reg2 = "r11";
              state.minusPush();
            }
            state.freeRegister(reg2);

            instructions.add(new Instruction("CMP " + reg1 + ", " + reg2));
            instructions.add(new Instruction("MOVGT " + reg1 + ", #1"));
            instructions.add(new Instruction("MOVLE " + reg1 + ", #0"));
            state.setResultRegister(reg1);
            break;
          case GREATER_THAN_EQ:
            checkOp1Type();
            reg1 = loadBinOpRegs(instructions, availReg, false, op1Type.toString().equals("char"));

            reg2 = state.getResultRegister();
            if (state.getPushCounter() > 0) {
              instructions.add(new Instruction("POP {r11}"));
              reg2 = "r11";
              state.minusPush();
            }
            state.freeRegister(reg2);

            instructions.add(new Instruction("CMP " + reg1 + ", " + reg2));
            instructions.add(new Instruction("MOVGE " + reg1 + ", #1"));
            instructions.add(new Instruction("MOVLT " + reg1 + ", #0"));
            state.setResultRegister(reg1);
            break;
          case SMALLER_THAN:
            checkOp1Type();
            reg1 = loadBinOpRegs(instructions, availReg, false, op1Type.toString().equals("char"));

            reg2 = state.getResultRegister();
            if (state.getPushCounter() > 0) {
              instructions.add(new Instruction("POP {r11}"));
              reg2 = "r11";
              state.minusPush();
            }
            state.freeRegister(reg2);

            instructions.add(new Instruction("CMP " + reg1 + ", " + reg2));
            instructions.add(new Instruction("MOVLT " + reg1 + ", #1"));
            instructions.add(new Instruction("MOVGE " + reg1 + ", #0"));
            state.setResultRegister(reg1);
            break;
          case SMALLER_THAN_EQ:
            checkOp1Type();
            reg1 = loadBinOpRegs(instructions, availReg, false, op1Type.toString().equals("char"));

            reg2 = state.getResultRegister();
            if (state.getPushCounter() > 0) {
              instructions.add(new Instruction("POP {r11}"));
              reg2 = "r11";
              state.minusPush();
            }
            state.freeRegister(reg2);

            instructions.add(new Instruction("CMP " + reg1 + ", " + reg2));
            instructions.add(new Instruction("MOVLE " + reg1 + ", #1"));
            instructions.add(new Instruction("MOVGT " + reg1 + ", #0"));
            state.setResultRegister(reg1);
            break;
          case EQ:
            boolean isBoolean;
            boolean isChar;

            if (op1IsIdent) {
              Type type = state.getType(operand1);
              isBoolean = type.toString().equals("bool");
              isChar = type.toString().equals("char");
            } else {
              isBoolean = op1Type.toString().equals("bool");
              isChar = op1Type.toString().equals("char");
            }

            reg1 = loadBinOpRegs(instructions, availReg, isBoolean, isChar);

            reg2 = state.getResultRegister();
            if (state.getPushCounter() > 0) {
              instructions.add(new Instruction("POP {r11}"));
              reg2 = "r11";
              state.minusPush();
            }
            state.freeRegister(reg2);

            instructions.add(new Instruction("CMP " + reg1 + ", " + reg2));
            instructions.add(new Instruction("MOVEQ " + reg1 + ", #1"));
            instructions.add(new Instruction("MOVNE " + reg1 + ", #0"));
            state.setResultRegister(reg1);
            break;
          case NOT_EQ:
            if (op1IsIdent) {
              Type type = state.getType(operand1);
              isBoolean = type.toString().equals("bool");
              isChar = type.toString().equals("char");
            } else {
              isBoolean = op1Type.toString().equals("bool");
              isChar = op1Type.toString().equals("char");
            }

            reg1 = loadBinOpRegs(instructions, availReg, isBoolean, isChar);

            reg2 = state.getResultRegister();
            if (state.getPushCounter() > 0) {
              instructions.add(new Instruction("POP {r11}"));
              reg2 = "r11";
              state.minusPush();
            }
            state.freeRegister(reg2);

            instructions.add(new Instruction("CMP " + reg1 + ", " + reg2));
            instructions.add(new Instruction("MOVNE " + reg1 + ", #1"));
            instructions.add(new Instruction("MOVEQ " + reg1 + ", #0"));
            state.setResultRegister(reg1);
            break;
          // extension - bitwise operator
          case BITWISE_AND:
          case AND:
            reg1 = loadBinOpRegs(instructions, availReg, op1Type.toString().equals("bool"), false);

            reg2 = state.getResultRegister();
            if (state.getPushCounter() > 0) {
              instructions.add(new Instruction("POP {r11}"));
              reg2 = "r11";
              state.minusPush();
            }
            state.freeRegister(reg2);

            instructions.add(new Instruction("AND " + reg1 + ", " + reg1 + ", " + reg2));
            state.setResultRegister(reg1);
            break;
          // extension - bitwise operator
          case BITWISE_OR:
          case OR:
            reg1 = loadBinOpRegs(instructions, availReg, op1Type.toString().equals("bool"), false);

            reg2 = state.getResultRegister();
            if (state.getPushCounter() > 0) {
              instructions.add(new Instruction("POP {r11}"));
              reg2 = "r11";
              state.minusPush();
            }
            state.freeRegister(reg2);

            instructions.add(new Instruction("ORR " + reg1 + ", " + reg1 + ", " + reg2));
            state.setResultRegister(reg1);
            break;
          // extension - bitwise operator
          case BITWISE_XOR:
            reg1 = loadBinOpRegs(instructions, availReg, false, false);

            reg2 = state.getResultRegister();
            if (state.getPushCounter() > 0) {
              instructions.add(new Instruction("POP {r11}"));
              reg2 = "r11";
              state.minusPush();
            }
            state.freeRegister(reg2);

            instructions.add(new Instruction("EOR " + reg1 + ", " + reg1 + ", " + reg2));
            state.setResultRegister(reg1);
            break;
        }
        break;
    }

    return instructions;
  }

  public void checkOp1Type() {
    if (op1Type == null) {
      op1Type = state.getType(operand1);
    }
  }
}
