This file contains the projects under development.

Update logging:
1. This logging will be further updated & more specific in the future. This is the initial version.

Version 1.0.0: 1st time submission. 
1. Completes the initial architect design & the environment establishment & revelant framework configuration.
2. Completes the initial functionality design, component development front and back end (excluding CSS so far).
3. More details refer to the project.

Version 1.0.1:
1. Resolves a problem that when upload no image in "itemAdd.jsp", previous return URL will not be transmitted to the item action, so now the item can be correctly added.
2. Also corrects some comments.

Versoin 1.0.2:
1. Updates some features: annotation, aspects, etc.
2. Adds new annotation & aop feature & improve duplicate submission avoidance & session. Test is done & no new problem is found.

Version 1.0.3:
1. Improves the performance of the page bean, adresses a bug in the token validator interceptor, and introduce custom tag components for token validation.

Version 1.0.4:
1. Adds a order-by query to database, addressing the consequent issues & explore the optimal idea for single-table query (which so far can be implemented for module-by-module situation instead of general situation like interceptor, pagination, etc.).
2. Later improves the architect of the project & completes the exploration of the MyBatis optimization idea.

Version 1.0.5:
1. Addresses a JAR problem regarding JSP APIm re-arranges the user & test module architecture & suppliments the comment for necessary JSP views.

Version 1.0.6:
1. Updates a feature of checkbox regarding all-select, all-unselect, reverse-select, performs 2 methods to process String array parameters based on different applcation scenario with a custom ArrayUtils class, & addresses a potential defects on user & item module regarding array parameters in the back-end.

Version 1.0.7:
1. Updates the architect of JS, integrates the JQueryUI effects & addresses a potential bug regarding the data with Date type by implementing an interceptor & add a new utility class regarding to Date comparison.

Version 1.0.8:
1. Adds the summarization regarding the experience of Java Web development, JavaSE core development, software design pattern.
2. Boardens the testing functionality regarding JS.

Version 1.0.9:
1. Modifies the configuration regarding database & adds the summarization regarding database implementation.
2. Addresses the defects regarding static page generation result, JS return to item administraton list, and utility class method finalization.
3. Supplements a function regarding item republishment.

Version 1.1.0:
1. Addresses the defects regarding user module, including case sensitivity, login validation bug & corresponding configuration constants.
2. Includes the previous summarization of JavaWeb, including web service & workflow or business process management.
3. Inlucdes the testing regarding regex, IO stream, big number, message format, enumeration, etc.