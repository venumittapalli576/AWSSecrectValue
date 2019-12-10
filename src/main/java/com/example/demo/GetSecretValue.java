package com.example.demo;

import java.nio.ByteBuffer;
import java.util.Base64;

import com.amazonaws.services.applicationdiscovery.model.InvalidParameterException;
import com.amazonaws.services.applicationdiscovery.model.ResourceNotFoundException;
import com.amazonaws.services.athena.model.InvalidRequestException;
import com.amazonaws.services.datapipeline.model.InternalServiceErrorException;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.DecryptionFailureException;
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
import com.amazonaws.services.secretsmanager.model.GetSecretValueResult;


public class GetSecretValue {

	public static void getSecret() {

			    String secretName = "/secrets/my-app/db";
			    String region = "us-east-1";

			    // Create a Secrets Manager client
			    AWSSecretsManager client  = AWSSecretsManagerClientBuilder.standard()
			                                    .withRegion(region)
			                                    .build();
			    
			    // In this sample we only handle the specific exceptions for the 'GetSecretValue' API.
			    // See https://docs.aws.amazon.com/secretsmanager/latest/apireference/API_GetSecretValue.html
			    
			    String secret, decodedBinarySecret;
			    GetSecretValueRequest getSecretValueRequest = new GetSecretValueRequest()
			                    .withSecretId(secretName);
			    GetSecretValueResult getSecretValueResult = null;

			    try {
			        getSecretValueResult = client.getSecretValue(getSecretValueRequest);
			    } catch (DecryptionFailureException e) {
			        // Secrets Manager can't decrypt the protected secret text using the provided KMS key.
			        // Deal with the exception here, and/or rethrow at your discretion.
			        throw e;
			    } catch (InternalServiceErrorException e) {
			        // An error occurred on the server side.
			        // Deal with the exception here, and/or rethrow at your discretion.
			        throw e;
			    } catch (InvalidParameterException e) {
			        // You provided an invalid value for a parameter.
			        // Deal with the exception here, and/or rethrow at your discretion.
			        throw e;
			    } catch (InvalidRequestException e) {
			        // You provided a parameter value that is not valid for the current state of the resource.
			        // Deal with the exception here, and/or rethrow at your discretion.
			        throw e;
			    } catch (ResourceNotFoundException e) {
			        // We can't find the resource that you asked for.
			        // Deal with the exception here, and/or rethrow at your discretion.
			        throw e;
			    }

			    // Decrypts secret using the associated KMS CMK.
			    // Depending on whether the secret is a string or binary, one of these fields will be populated.
			    if (getSecretValueResult.getSecretString() != null) {
			        secret = getSecretValueResult.getSecretString();
			        System.out.println("venu:"+secret);
			    }
			    else {
			        decodedBinarySecret = new String(Base64.getDecoder().decode(getSecretValueResult.getSecretBinary()).array());
			        System.out.println("venu 2:"+decodedBinarySecret);
			    }

			   
	}
			}