console.log("inside my javascript")
var selectedfiels = document.getElementById("reference");

/*<![CDATA[*/
var myval = /*[[${student}]]*/ "Test";
/*]]>*/

//var dateofbirth =document.getElementById("dob");

selectedfiels.addEventListener("click", RefernceDob);
function RefernceDob()
{
    
var t=document.createTextNode(myval[0].dob);
var p = document.createElement("P");
//attach text to para
p.appendChild(t);
//attach para to div
selectedfiels.appendChild(p);

}

