
/**
 * WebserviceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package mygui;

    /**
     *  WebserviceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class WebserviceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public WebserviceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public WebserviceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for getLophoc method
            * override this method for handling normal response from getLophoc operation
            */
           public void receiveResultgetLophoc(
                    mygui.WebserviceStub.GetLophocResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getLophoc operation
           */
            public void receiveErrorgetLophoc(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getLogin method
            * override this method for handling normal response from getLogin operation
            */
           public void receiveResultgetLogin(
                    mygui.WebserviceStub.GetLoginResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getLogin operation
           */
            public void receiveErrorgetLogin(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for getHocsinh method
            * override this method for handling normal response from getHocsinh operation
            */
           public void receiveResultgetHocsinh(
                    mygui.WebserviceStub.GetHocsinhResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from getHocsinh operation
           */
            public void receiveErrorgetHocsinh(java.lang.Exception e) {
            }
                


    }
    