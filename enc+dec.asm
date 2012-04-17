			.data
input_string:		
			.asciiz "ScrambleThisMessage\n"		

			.text
			.globl main
main:
			la $a0, input_string
			li $v0, 4
			syscall
			la $t0, input_string
loop:
			lb $t1, 0($t0)
			beqz $t1, exit_loop		
			addi $t1, $t1, 2   		
       			sb $t1, 0($t0)
no_change:
			addiu $t0, $t0, 1		
			j loop
exit_loop:
			la $a0, input_string		
			li $v0, 4
			syscall

			la $t2, input_string
loop2:
			lb $t3, 0($t2)
			beqz $t3, exit_loop2
			subi $t3, $t3, 2
			sb $t3, 0($t2)
no_change2:
			addiu $t2, $t2, 1
			j loop2
exit_loop2:
			la $a0, input_string
			li $v0, 4
			syscall
						
			li $v0, 10			# exit
			syscall


