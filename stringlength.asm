str:
main:	
	la $t2,str		# Load string into $t2
	li $t1,0	
nextCh:
	lb $t0,($t2)		# Load first letter
	beqz $t0,strEnd		# If null, jump to end
	add $t1,$t1,1		# increment counter
	addi $t2, $t2, 1	# move "pointer:
	j nextCh
strEnd:
	la $a0,ans		# sys call to print message
	li $v0,4
	move $a0,$t1		# sys call to print length
	li $v0,1	
	la $a0,endl		# new line, not entirely necessary
	li $v0,4	
	li $v0,10		# exit	


