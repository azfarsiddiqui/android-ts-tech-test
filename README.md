# android-ts-tech-test

<h2>Activities</h2>
<b>SolutionListActivity</b> - Displays a list of 'solution' items fetched from the feed at https://api.myjson.com/bins/1quht. This list view supports '<b><i>Pull To Refresh</i></b>' UI paradigm by using SwipeRefreshLayout of support-v4. An item can be tapped to open its detail.
<br /><b>SolutionDetailActivity</b> - Displays the detail of a 'solution' item by opening its detail url in a WebView.
<br /><br />
<h2>External Libraries Used</h2>
<b>Volley</b> - Volley is being used to manage HTTP requests & Asynchronous image loading.
<br /><br />
<h2>Possible Enhancements</h2>
<b>Image load error</b> - Show an error image if an image fails to load OR give a tap to retry feature.
<br /><b>Tests</b> - Unit test can be written to verify integrity of different pieces of the code.
<br /><b>Orientation changes</b> - onSaveInstanceState() and onRestoreInstanceState() could have been used to manage state between orientation changes more gracefully. Specially in cases where the activity needs to have different layouts for different orientations.
