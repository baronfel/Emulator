input_string:
main:
			la $a0, input_string			# System call to display string
			li $v0, 4				# These lines can also be left out
			la $t0, input_string			# $t0
loop:
			lb $t1, 0($t0)				# Starts loop
			beqz $t1, exit_loop			# If null terminator, exit loop
			subi $t1, $t1, 2   			# Scramble letter
       		sb $t1, 0($t0)				# Store scrambled letter
no_change:
			addiu $t0, $t0, 1			# Increment pointer
			j loop		
exit_loop:
			li $v0, 10			# exit


