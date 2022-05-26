package apitesting.sampleproject;

import org.hamcrest.core.IsEqual;
import org.json.simple.JSONObject;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import static io.restassured.RestAssured.*;

import java.util.HashMap;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

public class TestCases {

	ResponseSpecification res;
	
	@BeforeClass
	public void setSpecification() {
		res = RestAssured.expect();
		res.statusLine("HTTP/1.1 200 OK");
		res.contentType(ContentType.JSON);
		ExtentReportManager.createReport();
		
		
	}
	
	@Test
	public void testFetchUser() {
		ExtentReportManager.test = ExtentReportManager.reports.startTest("testFetchUser","Fetchin User Details");
		ExtentReportManager.test.log(LogStatus.INFO, "Specifying Base URI", "https://reqres.in");
		ExtentReportManager.test.log(LogStatus.INFO, "API Call", "GET");
		ExtentReportManager.test.log(LogStatus.INFO, "Resource Route", "https://reqres.in/api/users?page=2");
		ExtentReportManager.test.log(LogStatus.INFO, "Value Compared", "Page");
		RestAssured.baseURI = "https://reqres.in";
		given().when().get("https://reqres.in/api/users?page=2").then().spec(res).assertThat().body("page", IsEqual.equalTo(2));
		
		
		
		
	}
	
	@Test
	public void testDelayedResponse() {
		ExtentReportManager.test = ExtentReportManager.reports.startTest("testDelayedResponse","Delayed Response");
		ExtentReportManager.test.log(LogStatus.INFO, "Specifying Base URI", "https://reqres.in");
		ExtentReportManager.test.log(LogStatus.INFO, "API Call", "GET");
		ExtentReportManager.test.log(LogStatus.INFO, "Resource Route", "https://reqres.in/api/users?delay=3");
		ExtentReportManager.test.log(LogStatus.INFO, "Value Compared", "Total");
		RestAssured.baseURI = "https://reqres.in";
		given().when().get("https://reqres.in/api/users?delay=3").then().spec(res).assertThat().body("total", IsEqual.equalTo(12));
		
	}
	
	@Test
	public void testFetchSingleUser() {
		ExtentReportManager.test = ExtentReportManager.reports.startTest("testFetchSingleUser","Fetching Single USer");
		ExtentReportManager.test.log(LogStatus.INFO, "Specifying Base URI", "https://reqres.in");
		ExtentReportManager.test.log(LogStatus.INFO, "API Call", "GET");
		ExtentReportManager.test.log(LogStatus.INFO, "Resource Route", "https://reqres.in/api/users/2");
		ExtentReportManager.test.log(LogStatus.INFO, "Value Compared", "ID and FIRST NAME");
		RestAssured.baseURI = "https://reqres.in";
		given().when().get("https://reqres.in/api/users/2").then().spec(res).assertThat().body("data['id']", IsEqual.equalTo(2));
		given().when().get("https://reqres.in/api/users/2").then().spec(res).assertThat().body("data['first_name']", IsEqual.equalTo("Janet"));
		
	}
	
	
	
	
	// POST
	
	@Test
	public void testCreateUser() {
		ExtentReportManager.test = ExtentReportManager.reports.startTest("testCreateUser","Test Create User");
		ExtentReportManager.test.log(LogStatus.INFO, "Specifying Base URI", "https://reqres.in");
		ExtentReportManager.test.log(LogStatus.INFO, "API Call", "POST");
		ExtentReportManager.test.log(LogStatus.INFO, "Resource Route", "https://reqres.in/api/users");
		ExtentReportManager.test.log(LogStatus.INFO, "Value Compared", "Name");
		RestAssured.baseURI = "https://reqres.in";
		HashMap<String,String> params = new HashMap();
		params.put("name", "morpheus");
		params.put("job", "QA");
		given().when().contentType("application/json").body(params).post("https://reqres.in/api/users").then().assertThat().body("name", IsEqual.equalTo("morpheus"));
		
		
		
	}
	
	
	@Test
	public void testRegisterUser() {
		ExtentReportManager.test = ExtentReportManager.reports.startTest("testRegisterUser","Testing Resgister User");
		ExtentReportManager.test.log(LogStatus.INFO, "Specifying Base URI", "https://reqres.in");
		ExtentReportManager.test.log(LogStatus.INFO, "API Call", "POST");
		ExtentReportManager.test.log(LogStatus.INFO, "Resource Route", "https://reqres.in/api/register");
		ExtentReportManager.test.log(LogStatus.INFO, "Value Compared", "ID");
		RestAssured.baseURI = "https://reqres.in";
		HashMap<String,String> params = new HashMap();
		params.put("email", "eve.holt@reqres.in");
		params.put("password", "pistol");
		given().when().contentType("application/json").body(params).post("https://reqres.in/api/register").then().assertThat().body("id", IsEqual.equalTo(4));
	}
	
	@Test
	public void testLoginUser() {
		ExtentReportManager.test = ExtentReportManager.reports.startTest("testLoginUser","Login user Response");
		ExtentReportManager.test.log(LogStatus.INFO, "Specifying Base URI", "https://reqres.in");
		ExtentReportManager.test.log(LogStatus.INFO, "API Call", "POST");
		ExtentReportManager.test.log(LogStatus.INFO, "Resource Route", "https://reqres.in/api/login");
		ExtentReportManager.test.log(LogStatus.INFO, "Value Compared", "Token");
		RestAssured.baseURI = "https://reqres.in";
		HashMap<String,String> params = new HashMap();
		params.put("email", "eve.holt@reqres.in");
		params.put("password","cityslicka");
		given().when().contentType("application/json").body(params).post("https://reqres.in/api/login").then().assertThat().body("token", IsEqual.equalTo("QpwL5tke4Pnpja7X4"));
	}
	
	@Test
	public void testUnsuccesfulRegisterUser() {
		ExtentReportManager.test = ExtentReportManager.reports.startTest("testUnsuccesfulRegisterUser","Unsuccesful Register Users");
		ExtentReportManager.test.log(LogStatus.INFO, "Specifying Base URI", "https://reqres.in");
		ExtentReportManager.test.log(LogStatus.INFO, "API Call", "POST");
		ExtentReportManager.test.log(LogStatus.INFO, "Resource Route", "https://reqres.in/api/register");
		ExtentReportManager.test.log(LogStatus.INFO, "Value Compared", "Error Message");
		RestAssured.baseURI = "https://reqres.in";
		HashMap<String,String> params = new HashMap();
		params.put("email", "eve.holt@reqres.in");
		
		given().when().contentType("application/json").body(params).post("https://reqres.in/api/register").then().assertThat().body("error", IsEqual.equalTo("Missing password"));
	}
	@Test
	public void testLoginUnseccusfulUser() {
		ExtentReportManager.test = ExtentReportManager.reports.startTest("testLoginUnseccusfulUser","Login of Unsuccesful User");
		ExtentReportManager.test.log(LogStatus.INFO, "Specifying Base URI", "https://reqres.in");
		ExtentReportManager.test.log(LogStatus.INFO, "API Call", "POST");
		ExtentReportManager.test.log(LogStatus.INFO, "Resource Route", "https://reqres.in/api/login");
		ExtentReportManager.test.log(LogStatus.INFO, "Value Compared", "Error Message");
		RestAssured.baseURI = "https://reqres.in";
		HashMap<String,String> params = new HashMap();
		params.put("email", "peter@klaven");
		
		given().when().contentType("application/json").body(params).post("https://reqres.in/api/login").then().assertThat().body("error", IsEqual.equalTo("Missing password"));
	}
	
	@AfterClass
	public void closeReport() {
		ExtentReportManager.reports.flush();
	}
	
	
}
