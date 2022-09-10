package com.example.appnomiodev;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class JSONWorkbench {

    private String url = "https://api.shopiroller.com/v2.0/categories";

    private Context mContext;
    public boolean finishStatus;

    public JSONWorkbench(Context context) {
        mContext = context;
    }

    public ArrayList GET() {
        finishStatus = false;
        ArrayList<ArrayList> arrayListArrayList = new ArrayList<>();
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject respObj = new JSONObject(response);
                    String success = respObj.getString("success");
                    String data = respObj.getString("data");
                    try {
                        JSONArray jsonarray = new JSONArray(data);
                        for (int i = 0; i < jsonarray.length(); i++) {
                            ArrayList<String> arrayList = new ArrayList<>();
                            JSONObject jsonobject = jsonarray.getJSONObject(i);
                            Iterator<String> iter = jsonobject.keys();
                            while (iter.hasNext()) {
                                String key = iter.next();
                                try {
                                    arrayList.add(jsonobject.get(key).toString());
                                    Log.e("json", "key: " + jsonobject.get(key).toString());
                                } catch (JSONException e) {
                                    Log.e("json", "PARSE ERROR: " + e);
                                }
                            }
                            arrayListArrayList.add(arrayList);
                        }
                        finishStatus = true;
                    } catch (JSONException e) {
                        Log.e("json", "" + e);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("volley", "" + error);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Api-Key", "xXspnfUxPzOGKNu90bFAjlOTnMLpN8veiixvEFXUw9I=");
                params.put("Alias-Key", "AtS1aPFxlIdVLth6ee2SEETlRxk=");
                return params;
            }
        };
        requestQueue.add(stringRequest);
        return arrayListArrayList;
    }

    public ArrayList GETPRODUCTS(String category_id, String sort_by) {
        url = "https://api.shopiroller.com/v2.0/products/advanced-filtered?categoryId=" + category_id + "&sort=" + sort_by;
        finishStatus = false;
        ArrayList<ProductItem> productItems = new ArrayList<>();
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                Log.e("getproducts", "" + response);
                String id, image, title, desc;
                int stock;
                double price, campaignPrice;
                try {
                    JSONObject respObj = new JSONObject(response);
                    JSONArray jsonarray = new JSONArray(respObj.getString("data"));
                    for (int i = 0; i < jsonarray.length(); i++) {
                        JSONObject jsonobject = jsonarray.getJSONObject(i);
                        id = jsonobject.getString("id");
                        title = jsonobject.getString("title");
                        desc = jsonobject.getString("description");
                        stock = jsonobject.getInt("stock");
                        price = jsonobject.getDouble("price");
                        if (jsonobject.has("campaignPrice"))
                            campaignPrice = jsonobject.getDouble("campaignPrice");
                        else campaignPrice = -1;
                        image = jsonobject.getJSONArray("images").getJSONObject(0).getString("n");
                        ProductItem productItem = new ProductItem(id, image, title, desc, stock, price, campaignPrice);
                        productItems.add(productItem);
//                        Log.e("getproducts", "\nid: " + id + "\nimage: "+image+"\ntitle: " + title +"\ndesc: "+desc+ "\nstock: "+stock+"\nprice: " + price + "\ncampprice: " + campaignPrice);
                    }
                    finishStatus = true;
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("getproducts", "HATA\n\n" + e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("getproducts", "" + error);
            }

        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Api-Key", "xXspnfUxPzOGKNu90bFAjlOTnMLpN8veiixvEFXUw9I=");
                params.put("Alias-Key", "AtS1aPFxlIdVLth6ee2SEETlRxk=");
                return params;
            }
        };
        requestQueue.add(stringRequest);
        return productItems;
    }

}
