<!--- Please do not ask questions or create discussion in the bug tracker. Use https://nukkitx.com -->
<!--- ONLY POST ISSUES WITH A CLEAN SERVER ON THE LATEST VERSION -->
## Generated Bug Report

<!--- DO NOT OPEN A ISSUE IF THIS IS A PLUGIN ERROR -->
PLUGIN ERROR: TRUE

### Expected Behavior
<!--- What would you expect to happen -->


### Actual Behavior
<!--- What actually happened -->


### Steps to Reproduce
<!--- Reliable steps which someone can use to reproduce the issue. Please do not create issues for non reproducible bug! -->


### OS and Versions

* Nukkit Version: git-e8694c2 
* Java Version: Java HotSpot(TM) 64-Bit Server VM (1.8.0_333-b02)

* Host Configuration: 

| Item | Value |
|:----:|:-----:|
| Host OS | Windows 10-amd64 [10.0] |  
| Memory(RAM) | 16.9 GB | 
| Storage Size | 511.5 GB | 
| Storage Type | Disk 0:(avail=450.7 GB, total=511.5 GB)  | 
| CPU Type | Intel64 Family 6 Model 140 Stepping 1, GenuineIntel | 
| CPU Core Count | 8 | 

### Crashdump, Backtrace or Other Files

```
java.lang.NoClassDefFoundError: com/liveweather/storage/Zippie
	at com.liveweather.events.OnStartup.execute(OnStartup.java:21)
	at java.lang.Thread.run(Unknown Source)
Caused by: java.lang.ClassNotFoundException: com.liveweather.storage.Zippie
	at java.net.URLClassLoader.findClass(Unknown Source)
	at cn.nukkit.plugin.PluginClassLoader.findClass(PluginClassLoader.java:44)
	at cn.nukkit.plugin.PluginClassLoader.findClass(PluginClassLoader.java:28)
	at java.lang.ClassLoader.loadClass(Unknown Source)
	at java.lang.ClassLoader.loadClass(Unknown Source)
	... 2 more

```