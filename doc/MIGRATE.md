# Card Library : Migrate from cardslib 1.+ to 2.0

In this page you can find info about:

* [Intro](#intro)
* [Breaking changes](#breaking-changes)
* [Migrate the old card to the new CardViewNative](#migrate-the-old-card-to-the-new-cardviewnative)
* [Current open points](#current-open-points)

## Intro

The cardslib 2.+ introduces a new view called `CardViewNative` which extends the `android.support.v7.widget.CardView` released by Google with the support-library
 (`com.android.support:cardview-v7`). 

With this release you can build your `Cards` using one of 2 supported views:
* `it.gmariotti.cardslib.library.view.CardViewNative` which extends the Google's CardView.
* `it.gmariotti.cardslib.library.view.CardView`: it is the `CardView`-v1. 


You can continue to use the old `CardView`-v1 with a few changes in your code, or you can migrate to the `CardViewNative`.
In this page you will find all info to migrate from v1 to v2.


## Breaking changes

The library 2.x introduces some breaking changes in your code. You can fix these issues following this guide:

* if you are using the `getCardView()` you have to change your code using a cast to `CardView`. Now this code returns the `CardViewWrapper` interface.
``` java
   CardView cardView = (CardView) card.getCardView();
``` 

* If you are using an adapter which implements the `MultiChoiceAdapter`(`CardCursorMultiChoiceAdapter`,`CardArrayMultiChoiceAdapter`,`CardGridArrayMultiChoiceAdapter`) you have to change the signature of this method
From:
``` java
  public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked, CardView cardView, Card card)
```
To
```java
  public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked, CardViewWrapper cardView, Card card) {
```


## Migrate the old card to the new CardViewNative

You can use the cardslib 2.+ with the `CardView`-v1 and with the new `CardViewNative`.
If you would like to migrate the old cards to new view you have to follow these points:

* Change your xml from `it.gmariotti.cardslib.library.view.CardView` to `it.gmariotti.cardslib.library.view.CardViewNative` and adapt your java code.

* If you have a **custom [(global layout)](/doc/OVERVIEW.md)** you have to:
  - remove the tag `CardShadowView` because the shadow is not natively supported.
  - change the `LinearLayout` (with `id=@+id/card_main_layout`) with `it.gmariotti.cardslib.library.view.ForegroundLinearLayout`
  - if you haven't a custom innerLayout id in your `CardHeaderView` add the tag `card:card_header_layout_resourceID="@layout/native_base_header_layout"` (if you have, check the style below) 
  - if you haven't a custom innerLayout id in your `CardThumbnailView` add the tag `card:card_thumbnail_layout_resourceID="@layout/native_base_thumbnail_layout"` (if you have, check the style below) 
    
* If you are using the **other button in header** you can improve your code using a ripple for v21 and a selector for version<21 [(doc)](/doc/HEADER.md#standard-header-with-custom-button)

* *Styles* : if you have a **custom [(global layout)](/doc/OVERVIEW.md)** you should migrate the style of each component:

| Style CardView                          | Style CardViewNative                           | 
| --------------------------------------- |------------------------------------------------| 
| @style/card                             | @style/card.native                             | 
| @style/card.main_layout                 | card.native.main_layout_foreground             |
| @style/card.header_outer_layout         | @style/card.native.header_outer_layout         |
| @style/card.content_outer_layout        | @style/card.native.content_outer_layout        |
| @style/card_thumbnail_outer_layout      | @style/card.native.card_thumbnail_outer_layout |
| @style/main_contentExpand               | @style/card.native.main_contentExpand          |

* If you have a **custom layout for the header** you should migrate the style:
 
| Style CardView                          | Style CardViewNative                           | 
| --------------------------------------- |------------------------------------------------|
| @style/card.header_compound_view        | @style/card.native.header_compound_view        |
| @style/card.header_inner_frame          | @style/card.native.header_inner_frame          |
| @style/card.header_button_base          | @style/card.native.header_button_base          |
| @style/card.header_button_frame         | @style/card.native.header_button_frame         |
| @style/card.header_button_base.overflow | @style/card.native.header_button_base.overflow |
| @style/card.header_button_base.expand   | @style/card.native.header_button_base.expand   |
| @style/card.header_button_base.other    | @style/card.native.header_button_base.other    |
| @style/card.header_simple_title         | @style/card.native.header_simple_title         |

* If you have a **custom layout for the thumbnail** you should migrate the style:

| Style CardView                          | Style CardViewNative                           | 
| --------------------------------------- |------------------------------------------------|
| @style/card_thumbnail_image             | @style/card.native.card_thumbnail_image        |

* If you have a **custom layout for the main area** you should migrate the style:

| Style CardView                          | Style CardViewNative                           | 
| --------------------------------------- |------------------------------------------------|
| @style/card.base_simple_title           | @style/card.native.base_simple_title           |

* If you have a **custom layout for the expand area** you should migrate the style:

| Style CardView                          | Style CardViewNative                           | 
| --------------------------------------- |------------------------------------------------|
| @style/card.expand_simple_title         | @style/card.native.expand_simple_title         |

* If you are using a **CardWithList**, you should change the CardView to CardViewNative and add the attribute 
  `card:card_layout_resourceID="@layout/native_cardwithlist_layout"` in your CardView tag.

* **Lists**. To migrate a list you have to:
   - if you have the default layout add this tag to you list: `card:list_card_layout_resourceID="@layout/native_list_card_layout"`
   - if you are using the default layout with the thumbnail change your tag in your list: `card:list_card_layout_resourceID="@layout/native_list_card_thumbnail_layout"`
   - if you are using a custom `CardView` xml file for your item you have update the view type, the layout and the style:
     
     Example:
     ```xml
         <it.gmariotti.cardslib.library.view.CardViewNative           //change the tag with the new CardViewNative
            xmlns:android="http://schemas.android.com/apk/res/android"
            xmlns:card="http://schemas.android.com/apk/res-auto"
            android:id="@+id/list_cardId"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            style="@style/native_list_card.base"                       //use the native style
            card:card_layout_resourceID="@layout/native_card_layout"   //use the native layout or native_card_thumbnail_layout or YOUR LAYOUR
            />  
     ```
     If you are using a **CUSTOM LAYOUT**, check the points above and pay attention to this:
     You have to add a container to your `CardViewNative`
     Check this file: [`res/layout/native_list_card_layout`](/library-core/src/main/res/layout/native_list_card_layout.xml).
     
     
### Current open points     

There are some open points:

- **change color dynamically** (the current method is not a valid solution)
- **Contextual Action Bar and Toolbar** : the code inside the library works with the CAB and the classic ActionBar.
  Now you should migrate to the toolbar, and I have to find a good solution.


