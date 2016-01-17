<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>JS testing page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>

<script type = "text/javascript">
window.onload = function () {

	/*	This is the 1st example regarding closure & constructor & overload in JS	*/	
	console.log("The following is the 1st example regarding closure & constructor in JS:");
	function showName (firstName) {
	
		var nameIntro = "The name is";
		
		/*	This inner function that can access the outer function's variable	*/
		function showFullName (lastName) {
			console.log("The reference to the Object function before instantiation of \"showFullName()\":\n" + showFullName.constructor);		// Alert here: alerted result remains the same
			return nameIntro + " " + firstName + " " + lastName;
		};
		
		return showFullName;
	};
//	console.log(showName("Michael")("Jackson"));	// Overload error, since "showName(firstName)" has been replaced by "showName(firstName, lastName)"

	function showCompleteName (firstName) {
	
		var nameIntro = "The name is";
		
		/*	This inner function that can access the outer function's variable	*/
		function showFullName (lastName) {
			console.log("The reference to the Object function before instantiation of \"showFullName()\":\n" + showFullName.constructor);		// Alert here: alerted result remains the same
			return nameIntro + " " + firstName + " " + lastName;
		};
		
		return showFullName;
	};
	var completeName = showCompleteName("Michael")("Jackson");
	console.log("Return value from \"showCompleteName()\":\n" + completeName);
	console.log("The reference to the Object function after instantiation of \"showCompleteName()\":\n" + completeName.constructor);
	console.log("\n");
	
	/*	This is the 2nd example regarding closure & constructor & overload in JS	*/
	console.log("The following is the 2nd example regarding closure & constructor in JS:");	
	function showName (firstName, lastName) {
	
		var nameIntro = "The name is";
		
		/*	This inner function that can access the outer function's variable	*/
		function showFullName () {
			return nameIntro + " " + firstName + " " + lastName;
		};
		
		console.log("The reference to the Object function before instantiation of \"showName()\":\n" + showName.constructor);		// Alert here: alerted result remains the same
		return showFullName();
	};
	var fullName = showName("Michael", "Jackson");
	console.log("Return value from \"showName()\":\n" + fullName);
	console.log("The reference to the Object function after instantiation of \"showName()\":\n" + fullName.constructor);
	console.log("\n");
	
	/*	This is the 3rd example regarding closure & constructor in JS	*/
	console.log("The following is the 3rd example regarding closure & constructor in JS:");
	function createID () {
		
		var personID = 0;
		
		/*	Return a JSON object	*/
		return {
			getID: function () {
				return personID;
			},
			setID: function (newID) {
				personID = newID;
			}
		};
	};
	console.log("The reference to the Object function before instantiation of \"createID()\":\n" + createID.constructor);		// Alert here: alerted result remains the same
	var personID = createID();
	console.log("The reference to the Object function after instantiation of \"createID()\":\n" + personID.constructor);
	personID.setID(100);
	console.log("The new ID:\n" + personID.getID());
	console.log("\n");
	
	/*	
	 *	Conversion between JSON object and Function object
	 *  1. Cannot convert the function object to JSON object directly	
	 */
	var functionObj = personID.constructor;
//	console.log(JSON.parse(functionObj));			// Unexpected token error: unparsable
	console.log("The function object cannot be converted to JSON object directly.");
	console.log("\n");
	
	/*	
	 *	This part converts JSON object to string:	
	 *  1. Can convert what is return from function
	 */
	console.log("The following is to convert JSON object to string:");
	var person = {"name": "Vince", "id": personID.getID(), "gender": "male"};
	console.log("The JSON object: " + person);													// Output JSON object
	console.log("The JSON object: " + person.name + ", " + person.id + ", " +person.gender);	// Output JSON object
	var personString = JSON.stringify(person);						
	console.log("The string converted from JSON object using \"JSON.stringify()\":\n" + personString);		// Output string converted from JSON object
	console.log("\n");

	/*	This part converts string to JSON object	*/
	console.log("The following is to convert string to JSON object:");
	var string = "{\"name\": \"Vince\", \"age\": 25, \"gender\": \"male\"}";
	console.log("The string: " + string);
	var personJson = eval("(" + string +  ")");								// If string is put into a parenthese in eval(), will be treated as JS codes
	console.log("The JSON object converted from string using \"eval()\":\n" + personJson.name + ", " + personJson.age + ", " +personJson.gender);	// Output the JSON object converted from string
	personJson = JSON.parse(string);
	console.log("The JSON object converted from string using \"JSON.parse()\":\n" + personJson.name + ", " + personJson.age + ", " +personJson.gender);	// Output the JSON object converted from string	
	console.log("\n");
	
	/*	This part is to test prototype	*/
	console.log("The following is to test prototype:");
	console.log("The prototype of the \"showCompleteName()\" instance:\n" + completeName.prototype);		// Undefine: since prototype itself is not an implicit|unexisting object
	console.log("The prototype of the \"showName()\" instance:\n" + fullName.prototype);					// Undefine: since prototype itself is not an implicit|unexisting object
	console.log("The prototype of the \"createID()\" instance:\n" + personID.prototype);					// Undefine: since prototype itself is not an implicit|unexisting object
	console.log("Prototype is an implicit|unexisting object, hence above results are \"undefined\"");
	function Person (name, age, gender) {
		this.name = name;
		this.age = age;
		this.gender = gender;
	}
	var person = new Person("Vince", 25, "Male");
	Person.prototype.nationality = "US";		// Add new variables to the constructor through the corresponding prototype
	Person.prototype.getInfo = function () {	// Add new methods to the constructor through the corresponding prototype
		return this.name + ", " + this.age + ", " + this.gender + ", " + this.nationality;
	};
	var anotherPerson = new Person("Mary", 25, "Female");
	console.log("The person information from direct variable obtention:\n" + anotherPerson.name + ", " + anotherPerson.age + ", " + anotherPerson.gender + ", " + anotherPerson.nationality);
	console.log("The person information from new method \"getInfo()\"\n" + anotherPerson.getInfo());
};
</script>
  
  <body>
    <h4>Hello ${sessionScope.user.username}. Welcome to test MVNOLS</h4>
    To view the result: right click the web-page, "Inspect Element" --&gt; "Console" <br/><br/>
    <a href = "<c:url value = '/redirection/testModule.action'/>">Click here back to testing page</a>
  </body>
</html>
