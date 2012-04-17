package utility;

public enum Register {
	$ZERO(0, "$zero"),
	$V0(2, "$v0"),
	$V1(3, "$v1"),
	$A0(4, "$a0"),
	$A1(5, "$a1"),
	$A2(6, "a2"),
	$A3(7, "$a3"),
	$T0(8, "$t0"),
	$T1(9, "$t1"),
	$T2(10, "$t2"),
	$T3(11, "$t3"),
	$T4(12, "$t4"),
	$T5(13, "$t5"),
	$T6(14, "$t6"),
	$T7(15, "$t7"),
	$S0(16, "$s0"),
	$S1(17, "$s1"),
	$S2(18, "$s2"),
	$S3(19, "$s3"),
	$S4(20, "$s4"),
	$S5(21, "$s5"),
	$S6(22, "$s6"),
	$S7(23, "$s7"),
	$T8(24, "$t8"),
	$T9(25, "$t9"),
	$GP(28, "$gp"),
	$SP(29, "$sp"),
	$FP(30, "$fp"),
	$RA(31, "$ra"),
	$AT(1, "$at"),
	$K0(26, "$k0"),
	$K1(27, "k1"),
	$HI(32, "$hi"),
	$LO(33, "$lo"),
	$R0(0, "$r0"),
	$R1(1, "$r1"),
	$R2(2, "$r2"),
	$R3(3, "$r3"),
	$R4(4, "$r4"),
	$R5(5, "$r5"),
	$R6(6, "$r6"),
	$R7(7, "$r7"),
	$R8(8, "$r8"),
	$R9(9, "$r9"),
	$R10(10, "$r10"),
	$R11(11, "$r11"),
	$R12(12, "$r12"),
	$R13(13, "$r13"),
	$R14(14, "$r14"),
	$R15(15, "$r15"),
	$R16(16, "$r16"),
	$R17(17, "$r17"),
	$R18(18, "$r18"),
	$R19(19, "$19"),
	$R20(20, "$r20"),
	$R21(21, "$r21"),
	$R22(22, "$r22"),
	$R23(23, "$r23"),
	$R24(24, "$r24"),
	$R25(25, "$r25"),
	$R26(26, "$r26"),
	$R27(27, "$r27"),
	$R28(28, "$r28"),
	$R29(29, "$r29"),
	$R30(30, "$r30"),
	$R31(31, "$r31");
	
	int value;
	String name;
	Register(int value, String name){
		this.value = value;
		this.name = name.toLowerCase();
	}
	
	int getValue(){
		return value;
	}
	
	String getName(){
		return name;
	}

	public static String getName(int registerNumber) {
		for(Register r : Register.values())
		{
			if(r.getValue() == registerNumber)
			{
				return r.getName();
			}
		}
		return "Unknown Register";
	}
}
