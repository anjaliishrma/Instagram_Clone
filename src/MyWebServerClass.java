
import com.vmm.JHTTPServer;
import java.io.IOException;
import java.util.Properties;
import vmm.DBLoader;
import java.sql.*;

public class MyWebServerClass extends JHTTPServer {

    public MyWebServerClass(int port) throws IOException {
        super(port);
    }

    @Override
    public Response serve(String uri, String method, Properties header, Properties parms, Properties files) {
        Response res = new Response(HTTP_OK, "text/plain", "Server is Ready.");
        System.out.println("uri---------->" + uri);
        if (uri.contains("UserSignUp")) {

            String username = parms.getProperty("username");
            String password = parms.getProperty("password");
            String email = parms.getProperty("email");
            String phone = parms.getProperty("phone");
            String gender = parms.getProperty("gender");

            try {
                ResultSet rs = DBLoader.executeSQL("select * from user where username='" + username + "'");
                if (rs.next()) {
                    res = new Response(HTTP_OK, "text/plain", "fails");//username already exists:fails.
                } else {
                    String filename = saveFileOnServerWithRandomName(files, parms, "photo", "src/uploads");
                    String photoname = "src/uploads/" + filename;
                    rs.moveToInsertRow();
                    rs.updateString("username", username);
                    rs.updateString("email", email);
                    rs.updateString("password", password);
                    rs.updateString("gender", gender);
                    rs.updateString("phone", phone);
                    rs.updateString("photo", photoname);
                    rs.insertRow();
                    res = new Response(HTTP_OK, "text/plain", "success");

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (uri.contains("UserLogin")) {
//get username and password
//DBloader sql select * from users where username=username and password =password
            String username = parms.getProperty("username");
            String password = parms.getProperty("password");
            try {
                ResultSet rs = DBLoader.executeSQL("select * from user where username='" + username + "' and password='" + password + "'");
                if (rs.next()) {
                    res = new Response(HTTP_OK, "text/plain", "success");
                } else {
                    res = new Response(HTTP_OK, "text/plain", "fails");

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (uri.contains("changepassword")) {
            String username = parms.getProperty("username");
            String oldpassword = parms.getProperty("oldpassword");
            String newpassword = parms.getProperty("newpassword");
            try {
                ResultSet rs = DBLoader.executeSQL("select * from user where username='" + username + "' and password='" + oldpassword + "'");
                if (rs.next()) {
                    rs.updateString("password", newpassword);
                    rs.updateRow();

                    res = new Response(HTTP_OK, "text/plain", "success");
                } else {
                    res = new Response(HTTP_OK, "text/plain", "fails");

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (uri.contains("usersearch")) {
            String username = parms.getProperty("username");
            String keyword = parms.getProperty("keyword");

            try {
                ResultSet rs = DBLoader.executeSQL("select * from user where username like '" + keyword + "%' and username!='" + username + "'");
                String ans = "";

                while (rs.next()) {
                    String user = rs.getString("username");
                    String photo = rs.getString("photo");

                    ans += user + "!!" + photo + "~~";
                }

                res = new Response(HTTP_OK, "text/plain", ans);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (uri.contains("editdetailswithoutphoto")) {
            String username = parms.getProperty("username");
            String email = parms.getProperty("email");
            String phone = parms.getProperty("phone");
            try {
                ResultSet rs = DBLoader.executeSQL("select * from user where username='" + username + "' and email='" + email + "' and phone='" + phone + "'");
                if (rs.next()) {
                    rs.updateString("email", email);
                    rs.updateString("phone", phone);
                    rs.updateRow();

                    res = new Response(HTTP_OK, "text/plain", "success");
                } else {
                    res = new Response(HTTP_OK, "text/plain", "fails");

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (uri.contains("editdetailswithphoto")) {
            String username = parms.getProperty("username");
            String email = parms.getProperty("email");
            String phone = parms.getProperty("phone");
            try {
                ResultSet rs = DBLoader.executeSQL("select * from user where username='" + username + "' and email='" + email + "' and phone='" + phone + "'");
                if (rs.next()) {
                    rs.updateString("email", email);
                    rs.updateString("phone", phone);
                    rs.updateRow();

                    res = new Response(HTTP_OK, "text/plain", "success");
                } else {
                    res = new Response(HTTP_OK, "text/plain", "fails");

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (uri.contains("useraddpost")) {
            String username = parms.getProperty("username");
            String caption = parms.getProperty("caption");
//            String photo=parms.getProperty("photo");
            try {
                ResultSet rs = DBLoader.executeSQL("select * from post");
                String filename = saveFileOnServerWithRandomName(files, parms, "photo", "src/uploads");
                rs.moveToInsertRow();
                rs.updateString("username", username);
                rs.updateString("caption", caption);
                rs.updateString("photo", "src/uploads/" + filename);
                rs.insertRow();
                res = new Response(HTTP_OK, "text/plain", "success");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (uri.contains("useraddstory")) {
            String username = parms.getProperty("username");
            String caption = parms.getProperty("caption");
//            String photo=parms.getProperty("photo");
            try {
                ResultSet rs = DBLoader.executeSQL("select * from story");
                String filename = saveFileOnServerWithRandomName(files, parms, "photo", "src/uploads");
                rs.moveToInsertRow();
//                rs.updateString("username", username);
                rs.updateString("caption", caption);
                rs.updateString("photo", "src/uploads/" + filename);
                rs.insertRow();
                res = new Response(HTTP_OK, "text/plain", "success");
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else if (uri.contains("getAllposts")) {
            String username = parms.getProperty("username");
            
            try {
                ResultSet rs = DBLoader.executeSQL("select * from post where username='" + username + "'");
                String ans = "";
                while (rs.next()) {
                    String caption = rs.getString("caption");
                    String photo = rs.getString("photo");
                    int postid = rs.getInt("postid");

                    ans += caption + "!!" + photo + "!!" + postid + "~~";
                }
                res = new Response(HTTP_OK, "text/plain", ans);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if (uri.contains("getAllstories")) {
            String postid = parms.getProperty("post_id");
            
            try {
                ResultSet rs = DBLoader.executeSQL("select * from story where postid='" + postid + "'");
                String ans = "";
                while (rs.next()) {
                    String caption = rs.getString("caption");
                    String photo = rs.getString("photo");
                    int storyid = rs.getInt("storyid");

                    ans += caption + "!!" + photo + "!!" + storyid + "~~";
                }
                res = new Response(HTTP_OK, "text/plain", ans);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if (uri.contains("userforgotpassword")) {
            String username = parms.getProperty("username");
            try {
                ResultSet rs = DBLoader.executeSQL("select * from user where username='" + username + "'");
                if (rs.next()) {
                    int random = (int) (1000 + (9999 - 1000) * Math.random());
                    String otp = random + "";
                    res = new Response(HTTP_OK, "text/plain", otp);

                } else {
                    res = new Response(HTTP_OK, "text/plain", "fails");
                }

            } catch (Exception ex) {
                ex.printStackTrace();

            }
        } else if (uri.contains("usersetpassword")) {
            String username = parms.getProperty("username");
            String password = parms.getProperty("newpassword");
            System.out.println(username + " " + password);
            try {
                ResultSet rs = DBLoader.executeSQL("select * from user where username='" + username + "'");
                if (rs.next()) {
                    rs.updateString("password", password);
                    rs.updateRow();
                    res = new Response(HTTP_OK, "text/plain", "updated");

                } else {
                    res = new Response(HTTP_OK, "text/plain", "failed to update");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (uri.contains("removeFollowers")) {
            String fid = parms.getProperty("followerid");
            try {
                ResultSet rs = DBLoader.executeSQL("select * from followuser where followerid='" + fid + "'");
                if (rs.next()) {
                    rs.deleteRow();
                    res = new Response(HTTP_OK, "text/plain", "success");

                } else {
                    res = new Response(HTTP_OK, "text/plain", "fails");

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (uri.contains("myfollowing")) {
            String username = parms.getProperty("username");
            try {
                ResultSet rs = DBLoader.executeSQL("select * from followuser where followby='" + username + "'");
                String ans = "";
                while (rs.next()) {
                    String u = rs.getString("followto");
                    int followerid = rs.getInt("followerid");
                    ResultSet rs2 = DBLoader.executeSQL("select * from user where username='" + u + "'");
                    if (rs2.next()) {
                        String photo = rs2.getString("photo");

                        ans += u + ";;" + photo + ";;" + followerid + "&&";
                    }
                }
                res = new Response(HTTP_OK, "text/plain", ans);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (uri.contains("deletefollowing")) {
            String followerid = parms.getProperty("followerid");
            try {
                ResultSet rs = DBLoader.executeSQL("select * from followuser where followerid='" + followerid + "'");
                if (rs.next()) {
                    rs.deleteRow();
                    res = new Response(HTTP_OK, "text/plain", "success");
                } else {
                    res = new Response(HTTP_OK, "text/plain", "fails");

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (uri.contains("deletefollower")) {
            String followerid = parms.getProperty("followerid");
            try {
                ResultSet rs = DBLoader.executeSQL("select * from followuser where followerid='" + followerid + "'");
                if (rs.next()) {
                    rs.deleteRow();
                    res = new Response(HTTP_OK, "text/plain", "success");
                } else {
                    res = new Response(HTTP_OK, "text/plain", "fails");

                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (uri.contains("myfollower")) {
            String username = parms.getProperty("username");
            try {
                ResultSet rs = DBLoader.executeSQL("select * from followuser where followby='" + username + "'");
                String ans = "";
                while (rs.next()) {
                    String u = rs.getString("followto");
                    int followerid = rs.getInt("followerid");
                    ResultSet rs2 = DBLoader.executeSQL("select * from user where username='" + u + "'");
                    if (rs2.next()) {
                        String photo = rs2.getString("photo");

                        ans += u + ";;" + photo + ";;" + followerid + "&&";
                    }
                }
                res = new Response(HTTP_OK, "text/plain", ans);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (uri.contains("/GetResource")) {
            uri = uri.substring(1);
            uri = uri.substring(uri.indexOf("/") + 1);
            res = sendCompleteFile(uri);
            System.out.println("Response reference to be send to client-------------" + res);
        } else if (uri.contains("userfollow")) {
            String followby = parms.getProperty("followby");
            String followto = parms.getProperty("followto");
            try {
                ResultSet rs = DBLoader.executeSQL("select * from followuser where followby='" + followby + "' and followto='" + followto + "'");
                if (rs.next()) {
                    rs.deleteRow();
                    res = new Response(HTTP_OK, "text/plains", "fails");
                } else {
                    rs.moveToInsertRow();
                    rs.updateString("followby", followby);
                    rs.updateString("followto", followto);
                    rs.insertRow();
                    res = new Response(HTTP_OK, "text/plains", "SUCCESS");
                }
            } catch (Exception ex) {
                ex.printStackTrace();

            }

        } else if (uri.contains("fetchfollowingposts")) {
            String ans = "";

            String username = parms.getProperty("username");
            try {
                ResultSet rs = DBLoader.executeSQL("select * from followuser where followby='" + username + "'");
                while (rs.next()) {
                    String followto = rs.getString("followto");
                    ResultSet rs2 = DBLoader.executeSQL("select * from post where username='" + followto + "'");
                    while (rs2.next()) {
                        int post_id = rs2.getInt("postid");
                        String datetime = rs2.getString("Date_Time");
                        String caption = rs2.getString("caption");
                        String photo = rs2.getString("Photo");
                        String is_like = "";
                        int count_like = 0;
                        ResultSet rs3 = DBLoader.executeSQL("select * from like where postid=" + post_id + " and username='" + username + "'");
                        if (rs3.next()) {
                            is_like = "yes";
                        } else {
                            is_like = "no";
                        }
                        ResultSet rs4 = DBLoader.executeSQL("select count(*) from like where postid=" + post_id);
                        if (rs4.next()) {
                            count_like = rs4.getInt("count(*)");
                        }
                        ans = ans + followto + ";;" + post_id + ";;" + datetime + ";;" + photo + ";;" + is_like + ";;" + count_like + ";;" + caption + "~~";
                    }
                }
                res = new Response(HTTP_OK, "text/plain", ans);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        else if (uri.contains("/likepost")) {
            int postid = Integer.parseInt(parms.getProperty("postid"));
            String ans="";
            String username = parms.getProperty("username");
            try {
                ResultSet rs = DBLoader.executeSQL("select * from likepost where postid= " + postid + " and username='" + username + "'");
                if (rs.next()) {
                    rs.deleteRow();
                    ans = "unlike";
                } else {
                    rs.moveToInsertRow();
                    rs.updateString("username", username);
                    rs.updateInt("Postid", postid);
                    rs.insertRow();
                    ans = "like";
                }

                res = new Response(HTTP_OK, "text/plain", ans);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        return res;
    }

}
