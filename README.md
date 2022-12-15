# Android Firebase Cloud Messaging FCMSend

Sending Firebase Push Notifications - Android Device to Device

<a href='https://drive.google.com/file/d/1-3w141jsKGNAftqurWjCWCaY_Kv3vDuO/view?usp=sharing'><img src='https://www.scottishchildrenslottery.com/export/system/modules/com.assense.gaming.stv.template/resources/images/google-play-store.svg' alt='Download' height='45' /></a>

### Android Manifest

```  
<uses-permission android:name="android.permission.INTERNET"/>
```  

## Add FCMSend Gradle Plugin

```  
allprojects {  
    repositories { 
    ... 
    maven { url 'https://jitpack.io' } 
    }
}  
```  

```  
dependencies {  
    implementation 'com.github.uguraltinsoy:FCMSend:1.0.5'
}  
```
## Add Required Code

- Server Key = Firebase Console -> Select Project -> Project settings -> Cloud Messaging -> Copy
  Server Key

```java 
// JAVA
public class MainActivity extends AppCompatActivity {
    private static String serverKey = "";   
    @Override    
    protected void onCreate(Bundle savedInstanceState) {    
        super.onCreate(savedInstanceState);    
        setContentView(R.layout.activity_main);  
        // FCMSend Initialization  
        FCMSend.SetServerKey(serverKey);
    }
}  
```  

```kotlin  
// KOTLIN
class KotlinMainActivity : AppCompatActivity() {  
      private val serverKey = "" 
      override fun onCreate(savedInstanceState: Bundle?) { 
            super.onCreate(savedInstanceState) 
            setContentView(R.layout.activity_kotlin_main)
            // FCMSend Initialization
            FCMSend.SetServerKey(serverKey) 
      }
}  
```  

## Get Device Token and Subscribe To Topic

### Get Device Token

Get
device [Token](https://firebase.google.com/docs/cloud-messaging/android/client#retrieve-the-current-registration-token)

```java  
// JAVA  
FirebaseMessaging.getInstance().getToken() 
      .addOnCompleteListener(new OnCompleteListener<String>() {
          @Override    
          public void onComplete(@NonNull Task<String> task) {
              if (!task.isSuccessful()) { return; } 
              String token = task.getResult(); 
          }
      });  
```  

```kotlin  
// KOTLIN  
FirebaseMessaging.getInstance().token    
    .addOnCompleteListener(OnCompleteListener { task ->    
        if (!task.isSuccessful) {    
            return@OnCompleteListener    
        }    
val token = task.result })  
```  

### Subscribe To Topic

Subscribe [Topic](https://firebase.google.com/docs/cloud-messaging/android/topic-messaging)

```java  
// JAVA  
FirebaseMessaging.getInstance().subscribeToTopic("<Topic Name>")
    .addOnSuccessListener(new OnSuccessListener<Void>() {  
        @Override    
        public void onSuccess(Void aVoid) {  
        System.out.println("Subscription successful"); 
        }
    });  
```  

```kotlin  
// KOTLIN  
FirebaseMessaging.getInstance().subscribeToTopic("<Topic Name>")
    .addOnSuccessListener {  
        println("Subscription successful")
    }  
```  

## Usage Java - Kotlin

### Send Push Notifications By Device Token

```java  
// JAVA  
HashMap<String, String> data = new HashMap<>(); 
data.put("key1", "data 1"); 
data.put("key2", "data 2"); 
data.put("key3", "data 3");  
  
FCMSend.Builder build = new FCMSend.Builder("<To Device Token>")
    .setTitle("<Title>") 
    .setBody("<Message>") 
    .setImage("<Image Url>") // Optional 
    .setClickAaction("<Click Aaction>") // Optional 
    .setData(data); // Optional
build.send();  
```  

```kotlin  
// KOTLIN  
val data = HashMap<String, String>() 
data["key1"] = "data 1" 
data["key2"] = "data 2" 
data["key3"] = "data 3"    

val build = FCMSend.Builder("<To Device Token>") 
    .setTitle("<Title>")  
    .setBody("<Message>") 
    .setImage("<Image Url>") // Optional 
    .setClickAaction("<Click Aaction>") // Optional 
    .setData(data) // Optional  
build.send()  
```  

OR

```java  
// JAVA  
HashMap<String, String> data = new HashMap<>();  
data.put("key1", "data 1"); 
data.put("key2", "data 2"); 
data.put("key3", "data 3");  
  
FCMSend.Builder build = new FCMSend.Builder("<To Device Token>")
    .setTitle("<Title>") 
    .setBody("<Message>") 
    .setImage("<Image Url>") // Optional        
    .setClickAaction("<Click Aaction>") // Optional 
    .setData(data); // Optional  
String result = build.send().Result();  
```  

```kotlin  
// KOTLIN  
val data = HashMap<String, String>() 
data["key1"] = "data 1" 
data["key2"] = "data 2" 
data["key3"] = "data 3"  
  
val build = FCMSend.Builder("<To Device Token>") 
    .setTitle("<Title>")  
    .setBody("<Message>") 
    .setImage("<Image Url>") // Optional 
    .setClickAaction("<Click Aaction>") // Optional 
    .setData(data)  // Optional
val result = build.send().Result()  
```  

### Send Push Notifications By Topic

```java  
// JAVA  
HashMap<String, String> data = new HashMap<>(); 
data.put("key1", "data 1"); 
data.put("key2", "data 2"); 
data.put("key3", "data 3");  
  
FCMSend.Builder build = new FCMSend.Builder("<Topic Name>", true) 
    .setTitle("<Title>") 
    .setBody("<Message>") 
    .setImage("<Image Url>") // Optional 
    .setClickAaction("<Click Aaction>") // Optional 
    .setData(data); // Optional
build.send();  
```  

```kotlin  
// KOTLIN  
val data = HashMap<String, String>() 
data["key1"] = "data 1" 
data["key2"] = "data 2" 
data["key3"] = "data 3"  
  
val build = FCMSend.Builder("<Topic Name>", true)  
    .setTitle("<Title>")  
    .setBody("<Message>") 
    .setImage("<Image Url>") // Optional 
    .setClickAaction("<Click Aaction>") // Optional 
    .setData(data) // Optional
build.send()  
```  

OR

```Java  
// JAVA  
HashMap<String, String> data = new HashMap<>(); 
data.put("key1", "data 1"); 
data.put("key2", "data 2"); 
data.put("key3", "data 3");  
  
FCMSend.Builder build = new FCMSend.Builder("<Topic Name>", true)
    .setTitle("<Title>") 
    .setBody("<Message>") 
    .setImage("<Image Url>") // Optional 
    .setClickAaction("<Click Aaction>") // Optional 
    .setData(data); // Optional
String result = build.send().Result();  
```  

```kotlin  
// KOTLIN  
val data = HashMap<String, String>() 
data["key1"] = "data 1" 
data["key2"] = "data 2" 
data["key3"] = "data 3"  
  
val build = FCMSend.Builder("<Topic Name>", true)  
    .setTitle("<Title>") 
    .setBody("<Message>") 
    .setImage("<Image Url>") // Optional 
    .setClickAaction("<Click Aaction>") // Optional 
    .setData(data) // Optional
val result = build.send().Result()  
```

### If there is a click action

#### AndroidManifest

```xml
<application>
    <activity android:name=".MainActivity" android:exported="false">
        <intent-filter>
            <action android:name="android.intent.action.MAIN" />
            <category android:name="android.intent.category.LAUNCHER" />
        </intent-filter>
      
        <!-- Add -->
        <intent-filter>
            <action android:name="com.deeplabstudio.fcm_NOTIFICATION" />
            <category android:name="android.intent.category.DEFAULT" />
        </intent-filter>
        <!--     -->
    </activity>

    <service android:name=".PushNotificationService" android:exported="false">
        <intent-filter>
            <action android:name="com.google.firebase.MESSAGING_EVENT" />
        </intent-filter>
    </service>
</application>
```

#### Read Data

MainActivity

```java
// JAVA
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String url = bundle.getString("url");
        }
    }
}  
```

```kotlin
// KOTLIN
class KotlinMainActivity : AppCompatActivity() { 
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) setContentView (R.layout.activity_kotlin_main)
      
        val bundle = intent.extras
        if (bundle != null) {
            val url = bundle.getString("url")
        }
    }
}
```

### Developer By

```  
Uğur Altınsoy  
```  

### Donate

```  
BTC  : 1N7V3wX4xvGfwgBP1zQrcMSxohKKfiDxyH  
ETH  : 0x0df6da87e219fb4854e933f1071ad91d17afa517  
XRP  : rEb8TK3gBgk5auZkwc6sHnwrGVJH8DuaLh  
DOGE : DKKmSHAa8GhAE5HNjmCXzkXPKTjpybY3mq  
DENT : 0x0df6da87e219fb4854e933f1071ad91d17afa517  
```  

### Social

[![Twitter](https://img.shields.io/badge/twitter-%231DA1F2.svg?&style=for-the-badge&logo=twitter&logoColor=white)](https://twitter.com/uguraltnsy)  
[![Instagram](https://img.shields.io/badge/instagram-%23E4405F.svg?&style=for-the-badge&logo=instagram&logoColor=white)](https://www.instagram.com/ugur.altnsy)  
[![Linkedin](https://img.shields.io/badge/linkedin-%230077B5.svg?&style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/uğur-altınsoy/)  
[![Google Play](https://img.shields.io/badge/Google%20Play-414141?logo=google-play&logoColor=white&style=for-the-badge)](https://play.google.com/store/apps/developer?id=DeepLab&hl=tr)