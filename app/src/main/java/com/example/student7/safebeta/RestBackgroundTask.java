package com.example.student7.safebeta;
import com.example.student7.safebeta.RegisterActivity;
import com.example.student7.safebeta.RestClient;
import com.example.student7.safebeta.data.EmailAndPassword;
import com.example.student7.safebeta.data.User;
import com.example.student7.safebeta.data.Account;
import com.example.student7.safebeta.data.UserDB;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.rest.RestService;



@EBean
public class RestBackgroundTask {
   /* public static Integer zmiena = 3;
    Integer i = 0;
    @RootContext
    OtherRecActivity activity;
    @RootContext
    MyRecActivity activity1;
    @RootContext
    UlubioneActivity activity2;*/
   @RootContext
    RegisterActivity activity;
    @RestService
    RestClient restClient;

    /*@Background przerobic na pobranie usera
    void getCookBook() {
        try {
            restClient.setHeader("X-Dreamfactory-Application-Name", "cookbook");
            CookBook cookBook = restClient.getCookBook();
            /*for (Recipe recipe: cookBook.records) {
                if (recipe.picture1Id != null) {
                   Pictures pictures = restClient.getPictureById(recipe.picture1Id);
                    recipe.pictureBytes = pictures.base64bytes;
            }
            publishResult(cookBook);
        } catch (Exception e) {
            publishError(e);
        }
    }*/





    @Background
    void createUser(Account account){
        try {
            restClient.setHeader("X-Dreamfactory-Application-Name", "safetest");
            restClient.createUser(account);
        }
        catch (NullPointerException e){
            publishError(e);
        }
    }
    @Background
    void createUserDB(UserDB userDB){
        try {
            restClient.setHeader("X-Dreamfactory-Application-Name", "safetest");
            restClient.createUserDB(userDB);
        }
        catch (NullPointerException e){
            publishError(e);
        }
    }

   /* @Background
    void getSession() {

        try {
            restClient.setHeader("X-Dreamfactory-Application-Name", "safetest");
            Session session = restClient.getSession();

            publishResult1(session);
        } catch (Exception e) {
            publishError(e);
        }
    }*/



/*
    @UiThread
    void publishResult(CookBook cookBook) {
        try {
            activity.updateCookbook(cookBook);
        } catch ( NullPointerException e) {
            publishError(e);
        }
    }
   */
/*@UiThread
void publishResult1(Session session) {
    try {
        activity.sessionInfo(session);
    } catch ( NullPointerException e) {
        publishError(e);
    }
}*/
    @UiThread
    void publishError(Exception e) {
        activity.showError(e);
    }

}
