# Android Firebase Cloud Messaging FCMSend
Sending Firebase Push Notifications - Android Device to Device

### AndroidManifest
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
	implementation 'com.github.uguraltinsoy:FCMSend:1.0.1'
}
```
or Maven:
```
<repositories>
	<repository>
	    <id>jitpack.io</id>
	    <url>https://jitpack.io</url>
	</repository>
</repositories>
```
```
<dependency>
	<groupId>com.github.uguraltinsoy</groupId>
	<artifactId>FCMSend</artifactId>
	<version>1.0.0</version>
</dependency>
```
## Add Required Code
- Server Key = Firebase Console -> Select Project -> Project settings -> Cloud Messaging -> Copy Server Key
```Java 
// JAVA
import com.deeplabstudio.fcmsend.FCMSend;  

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
```Kotlin
// KOTLIN
import com.deeplabstudio.fcmsend.FCMSend

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
Get device [Token](https://firebase.google.com/docs/cloud-messaging/android/client#retrieve-the-current-registration-token)
```Java
// JAVA
FirebaseMessaging.getInstance().getToken()
	.addOnCompleteListener(new OnCompleteListener<String>() {  
		@Override  
		public void onComplete(@NonNull Task<String> task) {
			if (!task.isSuccessful()) {
				return;
			}
			String token = task.getResult();
	}  
});
```
```Kotlin
// KOTLIN
FirebaseMessaging.getInstance().token  
	.addOnCompleteListener(OnCompleteListener { task ->  
		if (!task.isSuccessful) {  
			return@OnCompleteListener  
		}  
		val token = task.result  
})
```
### Subscribe To Topic
Subscribe [Topic](https://firebase.google.com/docs/cloud-messaging/android/topic-messaging)
```Java
// JAVA
FirebaseMessaging.getInstance().subscribeToTopic("<Topic Name>").addOnSuccessListener(new OnSuccessListener<Void>() {  
	@Override  
	public void onSuccess(Void aVoid) {
		System.out.println("Subscription successful");
	}  
});
```
```Kotlin
// KOTLIN
FirebaseMessaging.getInstance().subscribeToTopic("<Topic Name>").addOnSuccessListener {
	println("Subscription successful")
}
```
## Usage Java - Kotlin

### Send Push Notifications By Device Token
```Java
// JAVA
HashMap<String, String> data = new HashMap<>();  
data.put("key1", "data 1");  
data.put("key2", "data 2");  
data.put("key3", "data 3");

FCMSend.Builder build = new FCMSend.Builder("<To Device Token>")
        .setTitle("<Title>")
        .setBody("<Message>")
        .setClickAction("<Action>") // Optional
        .setData(data); // Optional
build.send();
```
```Kotlin
// KOTLIN
val data = HashMap<String, String>()  
data["key1"] = "data 1"  
data["key2"] = "data 2"  
data["key3"] = "data 3"  
  
val build = FCMSend.Builder("<To Device Token>")  
  .setTitle("<Title>")  
  .setBody("<Message>")  
  .setClickAction("<Action>") // Optional  
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

FCMSend.Builder build = new FCMSend.Builder("<To Device Token>")
        .setTitle("<Title>")
        .setBody("<Message>")
        .setClickAction("<Action>") // Optional  
        .setData(data); // Optional
String result = build.send().Result();
```
```Kotlin
// KOTLIN
val data = HashMap<String, String>()  
data["key1"] = "data 1"  
data["key2"] = "data 2"  
data["key3"] = "data 3"

val build = FCMSend.Builder("<To Device Token>")  
  .setTitle("<Title>")  
  .setBody("<Message>")
  .setClickAction("<Action>") // Optional
  .setData(data)  // Optional
val result = build.send().Result()
```
### Send Push Notifications By Topic
```Java
// JAVA
HashMap<String, String> data = new HashMap<>();  
data.put("key1", "data 1");  
data.put("key2", "data 2");  
data.put("key3", "data 3");

FCMSend.Builder build = new FCMSend.Builder("<Topic Name>", true)
        .setTitle("<Title>")
        .setBody("<Message>")
        .setClickAction("<Action>") // Optional
        .setData(data); // Optional
build.send();
```
```Kotlin
// KOTLIN
val data = HashMap<String, String>()  
data["key1"] = "data 1"  
data["key2"] = "data 2"  
data["key3"] = "data 3"

val build = FCMSend.Builder("<Topic Name>", true)  
  .setTitle("<Title>")  
  .setBody("<Message>")
  .setClickAction("<Action>") // Optional
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
        .setClickAction("<Action>") // Optional
        .setData(data); // Optional
String result = build.send().Result();
```
```Kotlin
// KOTLIN
val data = HashMap<String, String>()  
data["key1"] = "data 1"  
data["key2"] = "data 2"  
data["key3"] = "data 3"

val build = FCMSend.Builder("<Topic Name>", true)
        .setTitle("<Title>")
        .setBody("<Message>")
        .setClickAction("<Action>") // Optional
        .setData(data) // Optional
val result = build.send().Result()
```
### Developer By
```
Uğur Altınsoy
```
<a href='https://play.google.com/store/apps/details?id=com.deeplabstudio.fcm'><img src='https://upload.wikimedia.org/wikipedia/commons/7/78/Google_Play_Store_badge_EN.svg' alt='Get it on Google Play' height='45' /></a>

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