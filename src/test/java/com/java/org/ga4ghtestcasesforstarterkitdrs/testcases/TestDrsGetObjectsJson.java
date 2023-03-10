package com.java.org.ga4ghtestcasesforstarterkitdrs.testcases;

import com.java.org.ga4ghtestcasesforstarterkitdrs.models.ErrorResponse;
import com.java.org.ga4ghtestcasesforstarterkitdrs.models.ResponseModel;
import com.java.org.ga4ghtestcasesforstarterkitdrs.utils.PropertyFileReader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.URI;
import java.util.Properties;

public class TestDrsGetObjectsJson {

    private final RestTemplate restTemplate;
    private final Properties properties;

    public TestDrsGetObjectsJson (){
        restTemplate = new RestTemplate();
        properties = PropertyFileReader.propertyReader();
    }

    @Test(testName = "DRS_GET_OBJECT_V1_API_J_01", description = "Verify successful GET request for one Object ID", priority = 1)
    public void getDrsObjects(){
        String objectId = "8e18bfb64168994489bc9e7fda0acd4f";
        String drsObjectsGetUrl = properties.getProperty("ga4gh.starter.drs.get").concat("/".concat(objectId));
        URI getDrsObjectURI = URI.create(drsObjectsGetUrl);
        ResponseEntity<ResponseModel> response = restTemplate.getForEntity(getDrsObjectURI, ResponseModel.class);
        ResponseModel responseModel = response.getBody();

        Assert.assertEquals(response.getStatusCode().value(), 200);
        Assert.assertNotNull(responseModel);
        Assert.assertEquals(responseModel.getId(), objectId);
    }


    @Test(testName = "DRS_GET_OBJECT_V1_API_J_02",
            description = "Verify successful GET request for one Object ID with ending two forward slashes", priority = 1)
    public void getDrsObjectsWithTwoForwardSlash(){
        String objectId = "8e18bfb64168994489bc9e7fda0acd4f";
        String drsObjectsGetUrl = properties.getProperty("ga4gh.starter.drs.get").concat("//".concat(objectId));
        URI getDrsObjectURI = URI.create(drsObjectsGetUrl);
        ResponseEntity<ResponseModel> response = restTemplate.getForEntity(getDrsObjectURI, ResponseModel.class);
        ResponseModel responseModel = response.getBody();

        Assert.assertEquals(response.getStatusCode().value(), 200);
        Assert.assertNotNull(responseModel);
        Assert.assertEquals(responseModel.getId(), objectId);
    }

    @Test(testName = "DRS_GET_OBJECT_V1_API_J_03",
            description = "Verify successful GET request for one Object ID by adding forward slash to the end of URL",
            priority = 1)
    public void getDrsObjectsByAddingForwardSlashToTheEnd(){
        String objectId = "8e18bfb64168994489bc9e7fda0acd4f";
        String drsObjectsGetUrl = properties.getProperty("ga4gh.starter.drs.get").concat("/".concat(objectId.concat("/")));
        URI getDrsObjectURI = URI.create(drsObjectsGetUrl);
        ResponseEntity<ResponseModel> response = restTemplate.getForEntity(getDrsObjectURI, ResponseModel.class);
        ResponseModel responseModel = response.getBody();

        Assert.assertEquals(response.getStatusCode().value(), 200);
        Assert.assertNotNull(responseModel);
        Assert.assertEquals(responseModel.getId(), objectId);
    }

    @Test(testName = "DRS_GET_OBJECT_V1_API_J_04",
            description = "Verify successful GET request for one Object ID by adding Hashtag to the end of URL",
            priority = 1)
    public void getDrsObjectsByAddingHashtagToTheEnd(){
        String objectId = "8e18bfb64168994489bc9e7fda0acd4f";
        String drsObjectsGetUrl = properties.getProperty("ga4gh.starter.drs.get").concat("/".concat(objectId.concat("#")));
        URI getDrsObjectURI = URI.create(drsObjectsGetUrl);
        ResponseEntity<ResponseModel> response = restTemplate.getForEntity(getDrsObjectURI, ResponseModel.class);
        ResponseModel responseModel = response.getBody();

        Assert.assertEquals(response.getStatusCode().value(), 200);
        Assert.assertNotNull(responseModel);
        Assert.assertEquals(responseModel.getId(), objectId);
    }

    @Test(testName = "DRS_GET_OBJECT_V1_API_J_05",
            description = "Verify successful GET request with different Object ID", priority = 1)
    public void getDrsObjectsWithDifferentObjectID(){
        String objectId = "ecbb0b5131051c41f1c302287c13495c";
        String drsObjectsGetUrl = properties.getProperty("ga4gh.starter.drs.get").concat("/".concat(objectId));
        URI getDrsObjectURI = URI.create(drsObjectsGetUrl);
        ResponseEntity<ResponseModel> response = restTemplate.getForEntity(getDrsObjectURI, ResponseModel.class);
        ResponseModel responseModel = response.getBody();

        Assert.assertEquals(response.getStatusCode().value(), 200);
        Assert.assertNotNull(responseModel);
        Assert.assertEquals(responseModel.getId(), objectId);
    }

    @Test(testName = "DRS_GET_OBJECT_V1_API_J_06",
            description = "Verify unsuccessful GET request with incorrect Object ID", priority = 1)
    public void getDrsObjectsWithIncorrectObjectID(){
        String objectId = "xx18bfb64168994489bc9e7fda0acd4f";
        String drsObjectsGetUrl = properties.getProperty("ga4gh.starter.drs.get").concat("/".concat(objectId));
        URI getDrsObjectURI = URI.create(drsObjectsGetUrl);

        Assert.assertThrows(HttpClientErrorException.NotFound.class, () ->{
            restTemplate.getForEntity(getDrsObjectURI, ErrorResponse.class);
        });

    }

    @Test(testName = "DRS_GET_OBJECT_V1_API_J_07",
            description = "Verify unsuccessful GET request without Object ID", priority = 1)
    public void getDrsObjectsWithoutObjectID(){
        String drsObjectsGetUrl = properties.getProperty("ga4gh.starter.drs.get");
        URI getDrsObjectURI = URI.create(drsObjectsGetUrl);

        Assert.assertThrows(HttpClientErrorException.MethodNotAllowed.class, () ->{
            restTemplate.getForEntity(getDrsObjectURI, ResponseModel.class);
        });
    }

}
