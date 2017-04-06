# Rest Service with RestTeamplate in Test Case
REST stands for Representational State Transfer.
#
<h3>In Rest based design, resources are being manipulated using a common set of verbs.</h3>

<ul>
<li>To Create a resource : HTTP POST should be used</li>
<li>To Retrieve a resource : HTTP GET should be used</li>
<li>To Update a resource : HTTP PUT should be used</li>
<li>To Delete a resource : HTTP DELETE should be used</li>
</ul>

<h2>Writing REST Client using RestTemplate</h2>
<p>
Postman tool we used above is a wonderful Client to test Rest API. But if you want to consume REST based web services from your application, you would need a REST client for your application. One of the most popular HTTP client is Apache HttpComponents HttpClient. But the details to access REST services using this are too low level.

Spring’s RestTemplate comes to Rescue. RestTemplate provides higher level methods that correspond to each of the six main HTTP methods that make invoking many RESTful services a one-liner and enforce REST best practices.

Below shown are HTTP methods and corresponding RestTemplate methods to handle that type of HTTP request.
</p>

<h3>HTTP Methods and corresponding RestTemplate methods:</h3>

<ul>
<li>HTTP GET : getForObject, getForEntity</li>
<li>HTTP PUT : put(String url, Object request, String urlVariables)</li>
<li>HTTP DELETE : delete</li>
<li>HTTP POST : postForLocation(String url, Object request, String  urlVariables), postForObject(String url, Object request, Class responseType, String…​ uriVariables)</li>
<li>HTTP HEAD : headForHeaders(String url, String urlVariables)</li>
<li>HTTP OPTIONS : optionsForAllow(String url, String urlVariables)</li>
<li>HTTP PATCH and others : exchange execute</li>
</ul>

<h2>Out put</h2>
https://github.com/NABEEL-AHMED-JAMIL/Rest_Service_RestTeamplate/blob/master/output/
<h3>Splite the Url</h3>
<p>
URI uri = new URI("http://example.com/foo/bar/42?param=true");<br>
String path = uri.getPath();<br>
String idStr = path.substring(path.lastIndexOf('/') + 1);<br>
int id = Integer.parseInt(idStr);<br>
<br>
alternatively<br>

URI uri = new URI("http://example.com/foo/bar/42?param=true");<br>
String[] segments = uri.getPath().split("/");<br>
String idStr = segments[segments.length-1];<br>
int id = Integer.parseInt(idStr);<br>
</p>
<h2>UML</h2>
<p>You can see the relationship of class in UML folder</p>
