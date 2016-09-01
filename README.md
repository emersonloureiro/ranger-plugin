# Ranger

Ranger is an eclipse plugin for finding tests - and thus functionality - that may pass through a given method in your Java code base. It will help you anticipate the impact of code changes, both functional and performance-related, allowing you to perform some smoke tests on core functionality, before actually breaking it!

Current version is 1.0.1, and requires Eclipse Juno or later. Currently Ranger only supports JUnit tests.

# Installation

Installation consists of both the core plugin - i.e., the GUI, searching algorithm, etc - and one one or more _test checker_ plugins, which define things specific to a testing framework - e.g., how a class/method can be identified as a test. 

## Eclipse update site

This will install both the core Ranger plugin, as well as the JUnit Test Checker plugin automatically. Less hassle!

http://dl.bintray.com/ranger/eclipse/1.0.1/

During the install, you may be faced with the dialog box below. Don't worry, you're safe to hit Ok and proceed with the installation

<img src="http://i.imgur.com/Nj4NxMj.png" width=450px/>

## Manually

Navigate to [this](https://github.com/emersonloureiro/ranger-plugin/tree/master/ranger-update-site/plugins) page, download the jars, and drop them into the _plugins_ folder of your Eclipse installation. Don't forget to restart Eclipse!

# Using the Plugin

To search for tests, simply place the cursor either on the method body or its signature, and run the search by right clicking on the Java file and selection the 'Search Tests' item. Alternatively, you can press 'Ctrl + Alt + T'.

![](http://i.imgur.com/RPAFWWL.png)

It's important to understand that if the cursor is placed on a method body, *that method* will be the one you will be search tests for, even if you are placing the cursor on a method call within another method's body. In the image below, for example, placing the cursor over the call to the `isDisabled()` method, will still cause the search to be performed for the outer method, `isTarget(CallHierarchyNode node)`.

![](http://i.imgur.com/qCDu94b.png)

The search is always performed on the background, so the UI isn't blocked. A search window does pop-up - unless you have configured Eclipse to always run tasks on the background - but it can be minimized. The search window also enables to cancel the current search.

![](http://i.imgur.com/1ujD9vn.png)

When the search is finished, the results found as well as the total number of them is displayed on the plugin's results view. The results can be grouped by project, class, or left ungrouped, in which case the test methods found are the top-level elements of the view. You can browse the results and follow the method call hierarchy from each of the tests found up to the target method. Double clicking on any of the methods will bring you to the exact location of the call in the source code. Likewise, double clicking on a class will open it on editor.

![](http://i.imgur.com/diZeKW8.png)

One search cannot be started while another is being processed. If that's the case, you can either wait for the current search to finish, or cancel it via the plugin's results view, the search window, or Eclipse's progress view - if the search window has already been minimized. When a search is cancelled, the results found up until that point are still displayed on the result's view.

To provide some customization, and prevent some potentially very long-running searches, there is an option to define the maximum depth allowed. So, a maximum search depth of 1, for example, will only return tests which are directly invoking the method you have searched for. The default value is 5, but you may want to adjust that if your code base is very large. The maximum search depth can be set either via the context menu of the results view or the plugin's preference page (below).

![](http://i.imgur.com/jppC74Z.png)
