package attune.client.api;

import attune.client.ApiException;
import attune.client.ApiInvoker;
import attune.client.AttuneConfigurable;
import attune.client.Version;
import attune.client.model.*;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;

import java.util.*;

/**
 * Created by sudnya on 5/26/15.
 */
public class Entities {
    AttuneConfigurable attuneConfig;
    ApiInvoker apiInvoker = ApiInvoker.getInstance();

    public Entities(AttuneConfigurable attuneConfig) {
        this.attuneConfig = attuneConfig;
    }

    public ApiInvoker getInvoker() {
        return apiInvoker;
    }

    /**
     * Returns a ranking of the specified entities for the current user.
     * @param params ranking params
     * @param accessToken authentication token
     * @return RankedEntities
     */
    public RankedEntities getRankings (RankingParams params, String accessToken) throws ApiException {
        // create path and map variables
        String path = "/entities/ranking";

        RankingParams modifiedParams = new RankingParams(params); //params;

        //which method: Use GET method for the API request unless entitySource=ids
        String method = params.getEntitySource().toUpperCase().equals("IDS") ? "POST" : "GET";
        // query params
        Map<String, String> queryParams  = new HashMap<String, String>();
        Map<String, String> headerParams = new HashMap<String, String>();

        queryParams.put("access_token", accessToken);

        if (params.getEntitySource().toUpperCase().equals("SCOPE")) {
            addScopesToQueryParams(params.getScopes(), queryParams);
            modifiedParams.setIds(null);
            addBodyParamsToQueryParams(modifiedParams, queryParams);
        }

        Object body = modifiedParams;

        String[] contentTypes = { };

        String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

        if(contentType.startsWith("multipart/form-data")) {
            boolean hasFields = false;
            FormDataMultiPart mp = new FormDataMultiPart();

            if(hasFields)
                body = mp;
        }

        try {
            String response = apiInvoker.invokeAPI(attuneConfig, path, method, queryParams, body, headerParams, contentType, Version.clientVersion);
            System.out.println(response);
            if(response != null) {
                return (RankedEntities) ApiInvoker.deserialize(response, "", RankedEntities.class);
            } else {
                throw new ApiException(503, "Response returned null");
            }
        } catch (ApiException ex) {
            throw ex;
        }
    }

    /**
     * Returns multiple rankings of the specified entities for the current user.
     * @param batchRequest Batch (list) of ranking requests
     * @param accessToken authentication token
     * @return BatchRankingResult List of ranking results for given list of requests
     */
    public BatchRankingResult batchGetRankings (BatchRankingRequest batchRequest, String accessToken) throws ApiException {
        // create path and map variables
        String path = "/entities/ranking/many";

        BatchRankingRequest modifiedBatchRequest = new BatchRankingRequest(batchRequest);

        //which method: GET for scopes, POST for ids
        String method = getMethodFromBatchEntitySource(batchRequest.getRequests());
        // query params
        Map<String, String> queryParams  = new HashMap<String, String>();
        Map<String, String> headerParams = new HashMap<String, String>();

        queryParams.put("access_token", accessToken);

        if (method.equals("GET")) {
            for (RankingParams params : modifiedBatchRequest.getRequests()) {
                addScopesToQueryParams(params.getScopes(), queryParams);
                params.setIds(null);
            }
        }

        Object postBody = modifiedBatchRequest;

        String[] contentTypes = {  };

        String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

        if(contentType.startsWith("multipart/form-data")) {
            boolean hasFields = false;
            FormDataMultiPart mp = new FormDataMultiPart();

            if(hasFields)
                postBody = mp;
        }

        try {
            String response = apiInvoker.invokeAPI(attuneConfig, path, method, queryParams, postBody, headerParams, contentType, Version.clientVersion);
            if(response != null) {
                return (BatchRankingResult) ApiInvoker.deserialize(response, "", BatchRankingResult.class);
            } else {
                throw new ApiException(503, "Response returned null");
            }
        } catch (ApiException ex) {
            throw ex;
        }
    }

    /**
     * List all blacklist entries for your account.
     * @return BlacklistGetResponse
     */
    public BlacklistGetResponse blacklistGetAll () throws ApiException {
        Object postBody = null;


        // create path and map variables
        String path = "/entities/rankings/blacklists";

        // query params
        Map<String, String> queryParams = new HashMap<String, String>();
        Map<String, String> headerParams = new HashMap<String, String>();

        String[] contentTypes = {  };

        String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

        if(contentType.startsWith("multipart/form-data")) {
            boolean hasFields = false;
            FormDataMultiPart mp = new FormDataMultiPart();

            if(hasFields)
                postBody = mp;
        }

        try {
            String response = apiInvoker.invokeAPI(attuneConfig, path, "GET", queryParams, postBody, headerParams, contentType, Version.clientVersion);
            if(response != null) {
                return (BlacklistGetResponse) ApiInvoker.deserialize(response, "", BlacklistGetResponse.class);
            } else {
                throw new ApiException(503, "Response returned null");
            }
        } catch (ApiException ex) {
            throw ex;
        }
    }

    /**
     * Save a new blacklist entry.
     * @param params params
     * @return BlacklistSaveResponse
     */
    public BlacklistSaveResponse blacklistSave (BlacklistParams params) throws ApiException {
        Object postBody = params;


        // create path and map variables
        String path = "/entities/rankings/blacklists";

        // query params
        Map<String, String> queryParams = new HashMap<String, String>();
        Map<String, String> headerParams = new HashMap<String, String>();

        String[] contentTypes = {  };

        String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

        if(contentType.startsWith("multipart/form-data")) {
            boolean hasFields = false;
            FormDataMultiPart mp = new FormDataMultiPart();

            if(hasFields)
                postBody = mp;
        }

        try {
            String response = apiInvoker.invokeAPI(attuneConfig, path, "POST", queryParams, postBody, headerParams, contentType, Version.clientVersion);
            if(response != null) {
                return (BlacklistSaveResponse) ApiInvoker.deserialize(response, "", BlacklistSaveResponse.class);
            } else {
                throw new ApiException(503, "Response returned null");
            }
        } catch (ApiException ex) {
            throw ex;
        }
    }

    /**
     * Returns the blacklist with the specified ID.
     * @param id id
     * @return Blacklist
     */
    public Blacklist blacklistGet (String id) throws ApiException {
        Object postBody = null;


        // create path and map variables
        String path = "/entities/rankings/blacklists/{" + apiInvoker.escapeString(id.toString()) + "}";

        // query params
        Map<String, String> queryParams = new HashMap<String, String>();
        Map<String, String> headerParams = new HashMap<String, String>();

        String[] contentTypes = {  };

        String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

        if(contentType.startsWith("multipart/form-data")) {
            boolean hasFields = false;
            FormDataMultiPart mp = new FormDataMultiPart();

            if(hasFields)
                postBody = mp;
        }

        try {
            String response = apiInvoker.invokeAPI(attuneConfig, path, "GET", queryParams, postBody, headerParams, contentType, Version.clientVersion);
            if(response != null) {
                return (Blacklist) ApiInvoker.deserialize(response, "", Blacklist.class);
            } else {
                throw new ApiException(503, "Response returned null");
            }
        } catch (ApiException ex) {
            throw ex;
        }
    }

    /**
     * Updates a blacklist with the specified ID.
     * @param id id
     * @param params params
     * @return BlacklistUpdateResponse
     */
    public BlacklistUpdateResponse blacklistUpdate (String id, BlacklistParams params) throws ApiException {
        Object postBody = params;


        // create path and map variables
        String path = "/entities/rankings/blacklists/{" + apiInvoker.escapeString(id.toString()) + "}";

        // query params
        Map<String, String> queryParams = new HashMap<String, String>();
        Map<String, String> headerParams = new HashMap<String, String>();

        String[] contentTypes = {  };

        String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

        if(contentType.startsWith("multipart/form-data")) {
            boolean hasFields = false;
            FormDataMultiPart mp = new FormDataMultiPart();

            if(hasFields)
                postBody = mp;
        }

        try {
            String response = apiInvoker.invokeAPI(attuneConfig, path, "PUT", queryParams, postBody, headerParams, contentType, Version.clientVersion);
            if(response != null) {
                return (BlacklistUpdateResponse) ApiInvoker.deserialize(response, "", BlacklistUpdateResponse.class);
            } else {
                throw new ApiException(503, "Response returned null");
            }
        } catch (ApiException ex) {
            throw ex;
        }
    }

    /**
     * Delete blacklist by id.
     * @param id id
     * @return BlacklistDeleteResponse
     */
    public BlacklistDeleteResponse blacklistDelete (String id) throws ApiException {
        Object postBody = null;

        // create path and map variables
        String path = "/entities/rankings/blacklists/{" + apiInvoker.escapeString(id.toString()) + "}";

        // query params
        Map<String, String> queryParams = new HashMap<String, String>();
        Map<String, String> headerParams = new HashMap<String, String>();

        String[] contentTypes = {  };

        String contentType = contentTypes.length > 0 ? contentTypes[0] : "application/json";

        if(contentType.startsWith("multipart/form-data")) {
            boolean hasFields = false;
            FormDataMultiPart mp = new FormDataMultiPart();

            if(hasFields)
                postBody = mp;
        }

        try {
            String response = apiInvoker.invokeAPI(attuneConfig, path, "DELETE", queryParams, postBody, headerParams, contentType, Version.clientVersion);
            if(response != null) {
                return (BlacklistDeleteResponse) ApiInvoker.deserialize(response, "", BlacklistDeleteResponse.class);
            } else {
                throw new ApiException(503, "Response returned null");
            }
        } catch (ApiException ex) {
            throw ex;
        }
    }

    private void addScopesToQueryParams(List<String> scopes, Map<String, String> queryParams) throws ApiException {
        if (scopes == null) {
            throw new ApiException(422, "Entity source is set to scope, but not scopes specified in RankingParams");
        }

        for (String scope : scopes) {
            String[] pieces = scope.split("=");

            //TODO: correct code number for this exception?
            if (pieces.length%2 != 0) {
                throw new ApiException(422, "scopes have to be in pairs");
            }
            for (int i = 0; i < pieces.length; i += 2) {
                queryParams.put(pieces[i], pieces[i + 1]);
            }

        }


    }

    private void addBodyParamsToQueryParams(RankingParams body, Map<String, String>queryParams) {

        queryParams.put("view", body.getView());
        queryParams.put("userAgent", body.getUserAgent());
        queryParams.put("anonymous", body.getAnonymous());
        queryParams.put("ip", body.getIp());
        queryParams.put("entityType", body.getEntityType());
        queryParams.put("application", body.getApplication());
        queryParams.put("customer", body.getCustomer());
        queryParams.put("quantities", body.getQuantities().toString());
        queryParams.put("scopes", body.getScopes().toString());
    }

    private String getMethodFromBatchEntitySource(List<RankingParams> batchRankings) throws ApiException {
        Set<String> retVal = new HashSet<>();
        for (RankingParams params : batchRankings) {
            String method = params.getEntitySource().toUpperCase().equals("IDS") ? "POST" : "GET";
            retVal.add(method);
        }
        if (retVal.size() != 1) {
            throw new ApiException(422, "cannot have different entity source in one batch request");
        }
        List<String> methods = new ArrayList<>();
        methods.addAll(retVal);
        return methods.get(0);
    }

}
