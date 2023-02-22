class Student{
    constructor(firstName,lastName,dob,gender,grade){
        //this.rollNumber=rollNumber;
        this.firstName=firstName;
        this.lastName=lastName;
        this.dob=dob;
        this.gender=gender;
        //this.email=email;
        //this.phoneNumber=phoneNumber;
        this.grade=grade;
    }

    printStudent(){
      
        return ` First Name: ${this.firstName} \n Last Name: ${this.lastName}
                 Date of Birth: ${this.dob} \n Gender: ${this.gender}
                  Grade: ${this.grade}`;
    }
}


const mysection = document.getElementById("section");

const displayText=()=>{
    text = document.getElementById("mypara");
    

    var studentinfo = new Array();
//studentinfo[0] = document.getElementById("rollNumber").value;
//console.log(studentinfo[0])
studentinfo[0] = document.getElementById("firstName").value;
console.log(studentinfo[0])
studentinfo[1] = document.getElementById("lastName").value;
studentinfo[2] = document.getElementById("dob").value;
studentinfo[3] = document.getElementById("gender").value;
//studentinfo[5] = document.getElementById("emailAddress").value;
//studentinfo[6] = document.getElementById("phoneNumber").value;
studentinfo[4] = document.getElementById("grade").value;

//  te.classList.add('btn', 'btn-outline-warning','col-12');
 text.innerText= new Student(studentinfo[0],studentinfo[1],studentinfo[2],studentinfo[3],studentinfo[4]).printStudent();
 
 
//  mysection.appendChild(text);
 }


 var studentinfodisplay = document.querySelector("#review");
studentinfodisplay.addEventListener('click',displayText);

const myFunctionName=()=> {
    if (document.myForm.myText.value === '[a-zA-Z]*$')
        return false;
        // When it returns false - your form will not submit and will not redirect too
    else
        return true;
     // When it returns true - your form will submit and will redirect
// (actually it's a part of submit) id you have mentioned in action
}


  