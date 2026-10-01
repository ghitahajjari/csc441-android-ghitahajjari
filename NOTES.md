1. My first build took approximatelly 5 minutes. The second took 3 minutes roughly.
2. The app changed to dark mode on its own.
3. I Don't understand what the different gradle files are and what each does.

Week 5, Friday: I changed the padding modifier on my column from 24.dp to 48.dp. This resilted in an increase of space and margins around all edges of the screen content, pushing the elements inward.



1. 2026-09-30 21:53:10.996 12287-12287 InputEventReceiver      edu.lemoyne.campusapp                E  Exception in NativeInputEventReceiver callbacks
2. 2026-09-30 21:53:10.996 12287-12287 InputEventReceiver      edu.lemoyne.campusapp                E  Failed to dispatch motion event to Java
3. 2026-09-30 21:53:10.997 12287-12287 AndroidRuntime          edu.lemoyne.campusapp                D  Shutting down VM
4. 2026-09-30 21:53:11.045 12287-12287 AndroidRuntime          edu.lemoyne.campusapp                E  FATAL EXCEPTION: main
5. &#x20;                                                                                                   Process: edu.lemoyne.campusapp, PID: 12287
6. &#x20;                                                                                                   java.lang.IndexOutOfBoundsException: index: -1, size: 0
7. &#x20;                                                                                                   	at androidx.compose.runtime.external.kotlinx.collections.immutable.internal.ListImplementation.checkElementIndex$runtime(ListImplementation.kt:15)
8. &#x20;                                                                                                   	at androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableList.SmallPersistentVector.get(SmallPersistentVector.kt:146)
9. &#x20;                                                                                                   	at androidx.compose.runtime.snapshots.SnapshotStateList.get(SnapshotStateList.android.kt:79)
10. &#x20;                                                                                                   	at androidx.compose.runtime.snapshots.SnapshotStateList.removeAt(SnapshotStateList.android.kt:127)
11. &#x20;                                                                                                   	at androidx.compose.runtime.snapshots.SnapshotStateList.remove(SnapshotStateList.android.kt:36)
12. &#x20;                                                                                                   	at edu.lemoyne.campusapp.MainActivityKt.HomeScreen$lambda$19$lambda$16$lambda$15(MainActivity.kt:142)
13. &#x20;                                                                                                   	at edu.lemoyne.campusapp.MainActivityKt$$ExternalSyntheticLambda6.invoke(D8$$SyntheticClass:0)
14. &#x20;                                                                                                   	at androidx.compose.foundation.ClickableNode.onPointerEvent-H0pRuoY(Clickable.kt:935)
15. &#x20;                                                                                                   	at androidx.compose.ui.input.pointer.Node.dispatchMainEventPass(HitPathTracker.kt:446)
16. &#x20;                                                                                                   	at androidx.compose.ui.input.pointer.Node.dispatchMainEventPass(HitPathTracker.kt:432)
17. &#x20;                                                                                                   	at androidx.compose.ui.input.pointer.NodeParent.dispatchMainEventPass(HitPathTracker.kt:285)
18. &#x20;                                                                                                   	at androidx.compose.ui.input.pointer.HitPathTracker.dispatchChanges(HitPathTracker.kt:181)
19. &#x20;                                                                                                   	at androidx.compose.ui.input.pointer.PointerInputEventProcessor.process-BIzXfog(PointerInputEventProcessor.kt:118)
20. &#x20;                                                                                                   	at androidx.compose.ui.platform.AndroidComposeView.sendMotionEvent-8iAsVTc(AndroidComposeView.android.kt:2685)
21. &#x20;                                                                                                   	at androidx.compose.ui.platform.AndroidComposeView.handleMotionEvent-8iAsVTc(AndroidComposeView.android.kt:2629)
22. &#x20;                                                                                                   	at androidx.compose.ui.platform.AndroidComposeView.dispatchTouchEvent(AndroidComposeView.android.kt:2467)
23. &#x20;                                                                                                   	at android.view.ViewGroup.dispatchTransformedTouchEvent(ViewGroup.java:3289)
24. &#x20;                                                                                                   	at android.view.ViewGroup.dispatchTouchEvent(ViewGroup.java:2974)
25. &#x20;                                                                                                   	at android.view.ViewGroup.dispatchTransformedTouchEvent(ViewGroup.java:3289)
26. &#x20;                                                                                                   	at android.view.ViewGroup.dispatchTouchEvent(ViewGroup.java:2974)
27. &#x20;                                                                                                   	at android.view.ViewGroup.dispatchTransformedTouchEvent(ViewGroup.java:3289)
28. &#x20;                                                                                                   	at android.view.ViewGroup.dispatchTouchEvent(ViewGroup.java:2974)
29. &#x20;                                                                                                   	at android.view.ViewGroup.dispatchTransformedTouchEvent(ViewGroup.java:3289)
30. &#x20;                                                                                                   	at android.view.ViewGroup.dispatchTouchEvent(ViewGroup.java:2974)
31. &#x20;                                                                                                   	at com.android.internal.policy.DecorView.superDispatchTouchEvent(DecorView.java:473)
32. &#x20;                                                                                                   	at com.android.internal.policy.PhoneWindow.superDispatchTouchEvent(PhoneWindow.java:2011)
33. &#x20;                                                                                                   	at android.app.Activity.dispatchTouchEvent(Activity.java:4713)
34. &#x20;                                                                                                   	at com.android.internal.policy.DecorView.dispatchTouchEvent(DecorView.java:416)
35. &#x20;                                                                                                   	at android.view.View.dispatchPointerEvent(View.java:17616)
36. &#x20;                                                                                                   	at android.view.ViewRootImpl$ViewPostImeInputStage.processPointerEvent(ViewRootImpl.java:9244)
37. &#x20;                                                                                                   	at android.view.ViewRootImpl$ViewPostImeInputStage.onProcess(ViewRootImpl.java:9061)
38. &#x20;                                                                                                   	at android.view.ViewRootImpl$InputStage.deliver(ViewRootImpl.java:8329)
39. &#x20;                                                                                                   	at android.view.ViewRootImpl$InputStage.onDeliverToNext(ViewRootImpl.java:8386)
40. &#x20;                                                                                                   	at android.view.ViewRootImpl$InputStage.forward(ViewRootImpl.java:8352)
41. &#x20;                                                                                                   	at android.view.ViewRootImpl$AsyncInputStage.forward(ViewRootImpl.java:8523)
42. &#x20;                                                                                                   	at android.view.ViewRootImpl$InputStage.apply(ViewRootImpl.java:8360)
43. &#x20;                                                                                                   	at android.view.ViewRootImpl$AsyncInputStage.apply(ViewRootImpl.java:8580)
44. &#x20;                                                                                                   	at android.view.ViewRootImpl$InputStage.deliver(ViewRootImpl.java:8333)
45. &#x20;                                                                                                   	at android.view.ViewRootImpl$InputStage.onDeliverToNext(ViewRootImpl.java:8386)
46. &#x20;                                                                                                   	at android.view.ViewRootImpl$InputStage.forward(ViewRootImpl.java:8352)
47. &#x20;                                                                                                   	at android.view.ViewRootImpl$InputStage.apply(ViewRootImpl.java:8360)
48. &#x20;                                                                                                   	at android.view.ViewRootImpl$InputStage.deliver(ViewRootImpl.java:8333)
49. &#x20;                                                                                                   	at android.view.ViewRootImpl.deliverInputEvent(ViewRootImpl.java:11881)
50. &#x20;                                                                                                   	at android.view.ViewRootImpl.doProcessInputEvents(ViewRootImpl.java:11825)
51. &#x20;                                                                                                   	at android.view.ViewRootImpl.enqueueInputEvent(ViewRootImpl.java:11793)
52. 2026-09-30 21:53:11.047 12287-12287 AndroidRuntime          edu.lemoyne.campusapp                E  	at android.view.ViewRootImpl.processRawInputEvent(ViewRootImpl.java:12231)
53. &#x20;                                                                                                   	at android.view.ViewRootImpl$WindowInputEventReceiver.onInputEvent(ViewRootImpl.java:12010)
54. &#x20;                                                                                                   	at android.view.InputEventReceiver.dispatchInputEvent(InputEventReceiver.java:385)
55. &#x20;                                                                                                   	at android.os.MessageQueue.nativePollOnce(Native Method)
56. &#x20;                                                                                                   	at android.os.MessageQueue.nextDeliQueue(MessageQueue.java:790)
57. &#x20;                                                                                                   	at android.os.MessageQueue.next(MessageQueue.java:770)
58. &#x20;                                                                                                   	at android.os.Looper.loopOnce(Looper.java:221)
59. &#x20;                                                                                                   	at android.os.Looper.loop(Looper.java:390)
60. &#x20;                                                                                                   	at android.app.ActivityThread.main(ActivityThread.java:9884)
61. &#x20;                                                                                                   	at java.lang.reflect.Method.invoke(Native Method)
62. &#x20;                                                                                                   	at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:575)
63. &#x20;                                                                                                   	at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:906)
64. 2026-09-30 21:53:11.141 12287-12287 Process                 edu.lemoyne.campusapp                I  Sending signal. PID: 12287 SIG: 9
65. 2026-09-30 21:53:11.156   790-1076  InputDispatcher         system\_server                        E  channel '412cad5 edu.lemoyne.campusapp/edu.lemoyne.campusapp.MainActivity' \~ Channel is unrecoverably broken and will be disposed!
66. 2026-09-30 21:53:11.174   534-534   SurfaceFlinger          surfaceflinger                       E  \[VRI-edu.lemoyne.campusapp/edu.lemoyne.campusapp.MainActivity#436] writeReleaseFence failed. error 32 (Broken pipe)
67. 2026-09-30 21:53:11.804   790-831   WindowOrga...Controller system\_server                        E  Attempting to externally change a non-organized container: Task{55bfa42 #48 type=standard A=10230:edu.lemoyne.campusapp}={handlePackageUpdate:false,} playercount=2 taskorg=android.window.ITaskOrganizer$Stub$Proxy@51a2d43



2\. The variable count changed but the screen didn't because composable was not told that the value changed. So mutableStateOf makes the variable count observable and composable is able to detect the state changes and causing the screen to change to the updated value. 



3\. "remember" is used so that the composable function keeps the value that is remembered instead of creating a new one every time the function runs. Without "remember", the state would be recreated each time the function is called so the value would keep resetting.

