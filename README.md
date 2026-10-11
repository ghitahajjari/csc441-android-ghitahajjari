### Playlist Log



Playlist Log is an Android app that helps users keep track of the songs they have listened to. Users can add songs from the Home screen and view their playlist on the List screen. On the List screen, users can see the songs they have added, remove individual songs, or remove all songs.



\## Screens



* \*\*Home\*\* — Allows users to add songs to their playlist.
* \*\*List\*\* — Displays the songs the user has added and the total number of songs. Users can view their songs, remove individual songs, remove all songs, and return to the Home screen.
* \*\*About\*\* — Provides information about the app.



\## Input Rules



| Input | What the app says |

|---|---|

| Empty name | Enter a music name. |

| Fewer than 3 characters | Too short — at least 3 characters. |

| More than 30 characters | Keep it to the maximum length characters. |

| Name containing only numbers | Song can't be just numbers. |

| Duplicate song name, ignoring capitalization | Song is already on the list. |



\## How to Run



1\. Clone this repository.

2\. Open the cloned project folder in Android Studio.

3\. Wait for Gradle sync to finish.

4\. Click \*\*Run\*\* to launch the app.



\## Known Limitations



The playlist Log resets when the acitivity is recreated,meaning when the phone rotates. The playlist alos does not save the playlist permanently, so if the app is closed and restarted, the songs that were Added or removed may no longer appear.



