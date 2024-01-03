function validate(frm){
    	//empty the form validation error message
        document.getElementById("pnameErr").innerHTML="";
    	document.getElementById("paddrsErr").innerHTML="";
    	document.getElementById("pageErr").innerHTML="";
    	//read form data
    	let name=frm.pname.value;
    	let addrs=frm.paddrs.value;
    	let age=frm.page.value;
    	
    	//client side form validation
    	let flag=true;
    	if(name==""){
    		//alert("Person name is required.");
    		document.getElementById("pnameErr").innerHTML="Person name is required.";
    		frm.pname.focus();//focus= cursor should blink there -makes text box gaining the focus
    		flag=false;
    	}
    	if(addrs==""){
    	//	alert("Person address is required.");
    	document.getElementById("paddrsErr").innerHTML="Person address is required.";
    		frm.paddrs.focus();
    		flag=false;
    	}
    	else if(addrs.length<10){
    		//alert("Person address must have 10 characters.");
    		document.getElementById("paddrsErr").innerHTML="Person address must have 10 characters.";
    		frm.paddrs.focus();
    		flag=false;
    	}
    	
    	if(age==""){
    		//alert("Person age is required");
    		document.getElementById("pageErr").innerHTML="Person age is required.";
    		frm.page.focus();
    		flag=false;
    	}
    	else if(isNaN(age)){
    		//alert("Person age must be anumber");
    		document.getElementById("pageErr").innerHTML="Person age must be a number.";
    		frm.page.focus();
    		flag=false;
    	}
    	else if(age<=0 || age>125){
    		//alert("Person age must be in between 1-125");
    		document.getElementById("pageErr").innerHTML="Person age must be in between 1-125.";
    		frm.page.focus();
    		flag=false;
    	}
    	//change hidden box value (vflag) to "yes" indicating client side form validations are done
    	frm.vflag.value="yes";
    	return flag;
    }//function