# Cards Library: Expand

In this page you can find info about:

* [Creating a base CardExpand](#creating-a-base-cardexpand)
* [Standard Expand](#standard-expand)
* [Background Color](#background-color)
* [Custom Expand inflating your inner layout](#custom-expand-inflating-your-inner-layout)
* [Listeners](#listeners)
* [Expand the card by clicking on different view](#expand-the-card-by-clicking-on-different-view)
* [Expand the card in programmatic way](#expand-the-card-in-programmatic-way)
* [Expand the card with a longClick](#expand-the-card-with-a-longclick)
* [CardExpand and CardListView](#cardexpand-and-cardlistview)
* [Style](#style)

### Creating a base CardExpand

Creating a base `CardExpand` is pretty simple.

``` java
        //This provide a simple (and useless) expand area
        CardExpand expand = new CardExpand(getContext());

        //Set inner title in Expand Area
        expand.setTitle(getString(R.string.demo_expand_basetitle));

        //Add expand to card
        card.addCardExpand(expand);
```

`CardExpand` provides a base Expand InnerLayout.

For the **CardViewNative**:
You can find it in [`res/layout/native_inner_base_expand.xml`](/library-core/src/main/res/layout/native_inner_base_expand.xml) 
  
For the **CardView**
You can find it in [`res/layout/inner_base_expand.xml`](/library-core/src/main/res/layout/inner_base_expand.xml).

The built-in InnerLayout provide these features:

* a **title**

There are many ways you can customize the card expand.

### Standard Expand

If you want a standard `CardExpand` you can use this simple code:

``` java
        //This provide a simple (and useless) expand area
        CardExpand expand = new CardExpand(getContext());

        //Set inner title in Expand Area
        expand.setTitle(getString(R.string.demo_expand_basetitle));

        //Add expand to a card
        card.addCardExpand(expand);
```

![Screen](/demo/images/header/native/expand.png)


### Custom Expand inflating your inner layout

If you want to customize the `CardExpand` you can extend the base class.

You can inflate your inner layout, then you can populate your layout with `setupInnerViewElements(ViewGroup parent, View view)` method

``` java
    public class CustomExpandCard extends CardExpand {

        //Use your resource ID for your inner layout
        public CustomExpandCard(Context context) {
            super(context, R.layout.carddemo_example_inner_expand);
        }

        @Override
        public void setupInnerViewElements(ViewGroup parent, View view) {

            if (view == null) return;

            //Retrieve TextView elements
            TextView tx1 = (TextView) view.findViewById(R.id.carddemo_expand_text1);
            TextView tx2 = (TextView) view.findViewById(R.id.carddemo_expand_text2);
            TextView tx3 = (TextView) view.findViewById(R.id.carddemo_expand_text3);
            TextView tx4 = (TextView) view.findViewById(R.id.carddemo_expand_text4);

            //Set value in text views
            if (tx1 != null) {
                tx1.setText(getContext().getString(R.string.demo_expand_customtitle1));
            }

            if (tx2 != null) {
                tx2.setText(getContext().getString(R.string.demo_expand_customtitle2));
            }
        }
}
```

Then you can add this custom `CustomExpandCard` to your `Card`:

``` java
        //This provide a simple (and useless) expand area
        CustomExpandCard expand = new CustomExpandCard(getContext());

        //Add Expand Area to a Card
        card.addCardExpand(expand);
```

![Screen](/demo/images/header/expandCustom.png)

### Background Color

You can change the background color overriding this value in your project:

``` xml
   <color name="card_backgroundExpand">#515254</color>
```

If you would like to change the color dynamically, in your `CardExpand` you can use this;

``` java
        @Override
        public void setupInnerViewElements(ViewGroup parent, View view) {
             parent.setBackgroundColor(mContext.getResources().
                      getColor(R.color.demo_card_background_color1));
        }
```

### Listeners

You can use these listeners to listen any callbacks when animations end.

* `card.OnExpandAnimatorEndListener` invoked  when expand animation ends
* `card.OnCollapseAnimatorEndListener` invoked when collapse animator ends.

Example:

``` java
        card.setOnExpandAnimatorEndListener(new Card.OnExpandAnimatorEndListener() {
            @Override
            public void onExpandEnd(Card card) {
                //Do something
            }
        });
```

Also you can use these listeners to listen any callbacks when animations start.

* `card.OnExpandAnimatorStartListener` invoked  when expand animation starts.
* `card.OnCollapseAnimatorStartListener` invoked when collapse animator starts.

Example:

``` java
        card.setOnExpandAnimatorStartListener(new Card.OnExpandAnimatorStartListener() {
            @Override
            public void onExpandStart(Card card) {
                //Do something
            }
        });
```

### Expand the card by clicking on different view

You can enable the expand/collapse action on different Views rather than the expand button on header.

Default way: it enables the expand button on the header.

``` java
        //Create a CardHeader
        CardHeader header = new CardHeader(getContext());

        //Set visible the expand/collapse button
        header.setButtonExpandVisible(true);
```

Pay attention: the method `header.setButtonExpandVisible(true)` has a higher priority.

You can enable the expand/collapse action by clicking on a different View.
You have to set the `ViewToClickToExpand` on the `Card`.

``` java
       ViewToClickToExpand.builder()
             .setupView(cardView); //setup the view which enables the expand/collapse action
             .highlightView(true); //true to highlight the view as selected (default=false)

```


How to enable expand/collapse action by clicking on the card.

``` java
        //Create a Card
        CustomCard card = new CustomCard(getActivity());

        //This provides a simple (and useless) expand area
        CardExpand expand = new CardExpand(getActivity());
        //Set inner title in Expand Area
        expand.setTitle(getString(R.string.demo_expand_basetitle));
        card.addCardExpand(expand);


        //Set card in the cardView
        CardView cardView = (CardView) getActivity().findViewById(R.id.carddemo_example_card_expand2);

        ViewToClickToExpand viewToClickToExpand =
                ViewToClickToExpand.builder()
                        .setupView(cardView);
        card.setViewToClickToExpand(viewToClickToExpand);

        cardView.setCard(card);
```

How to enable expand/collapse action by clicking on an element of the Card.

``` java
    public class CustomCard2 extends Card{

        public CustomCard2(Context context) {
            super(context,R.layout.carddemo_example_cardexpand_inner_content);
        }

        @Override
        public void setupInnerViewElements(ViewGroup parent, View view) {

            if (view != null) {
                TextView mTitleView = (TextView) view.findViewById(it.gmariotti.cardslib.library.R.id.card_main_inner_simple_title);
                if (mTitleView != null){
                    mTitleView.setText(mTitle);

                    ViewToClickToExpand viewToClickToExpand =
                            ViewToClickToExpand.builder()
                                    .setupView(mTitleView);
                    setViewToClickToExpand(viewToClickToExpand);
                }
            }
        }
    }

```


How to enable expand/collapse action by clicking on the image on Thumbnail.

``` java
   public class CustomThumbnail extends CardThumbnail{

          public CustomThumbnail(Context context) {
              super(context);
          }

          @Override
          public void setupInnerViewElements(ViewGroup parent, View imageView) {

              ViewToClickToExpand viewToClickToExpand =
                      ViewToClickToExpand.builder()
                              .setupView(imageView);
              getParentCard().setViewToClickToExpand(viewToClickToExpand);
          }
   }

```

You can see some examples in [`CardExpandFragment`](/demo/stock/src/main/java/it/gmariotti/cardslib/demo/fragment/native/NativeCardExpandFragment.java).


How to enable the custom expand/collapse in a `ListView`.
``` java
   public class MyCard extends Card{

        public MyCard(Context context) {
            super(context);
        }

        @Override
        public void setupInnerViewElements(ViewGroup parent, View view) {
            //Example on the card
            ViewToClickToExpand viewToClickToExpand = ViewToClickToExpand.builder().setupView(getCardView());
            setViewToClickToExpand(viewToClickToExpand);
        }
    }
```

This code requires that your `Card` has a MainContentLayout `android:id="@+id/card_main_content_layout"`, otherwise you can use the new method described below.


Also you can enable the expand/collapse action on the default card elements using this method:


``` java
   ViewToClickToExpand viewToClickToExpand =
                ViewToClickToExpand.builder()
                        .highlightView(false)
                        .setupCardElement(ViewToClickToExpand.CardElementUI.CARD);
   card.setViewToClickToExpand(viewToClickToExpand);
```

Pay attention: the method `ViewToClickToExpand.setupView(imageView);` has a higher priority than the method `ViewToClickToExpand.setupCardElement()`.

You can use this cardElementUI:

- `ViewToClickToExpand.CardElementUI.CARD`: to enable the click on the whole card
- `ViewToClickToExpand.CardElementUI.HEADER`: : to enable the click on the header
- `ViewToClickToExpand.CardElementUI.MAIN_CONTENT`: to enable the click on the main content (not the whole card)
- `ViewToClickToExpand.CardElementUI.THUMBNAIL`: to enable the click on the thumbnail (the whole thumbnail)

You can also use this feature with a `ListView`.

``` java
      public class MyCard extends Card{

           public MyCard(Context context) {
               super(context);
               init();
           }

           protected void init(){
               ViewToClickToExpand viewToClickToExpand =
                           ViewToClickToExpand.builder()
                                   .highlightView(false)
                                   .setupCardElement(ViewToClickToExpand.CardElementUI.CARD);
               card.setViewToClickToExpand(viewToClickToExpand);
           }
       }
```

You can see some examples in [`ExpandPicassoFragment`](/demo/extras/src/main/java/it/gmariotti/cardslib/demo/extras/fragment/native/ExpandPicassoFragment.java).

### Expand the card in programmatic way

You can expand and collapse the `Card` in programmatic way.

You have to set the `CardExpand` and you layout as described above.

Also if you card **hasn't** a `ViewToClickToExpand` you have to set it.

``` java
    ViewToClickToExpand viewToClickToExpand = ViewToClickToExpand.builder().enableForExpandAction();
    card.setViewToClickToExpand(viewToClickToExpand);
```

Then in your code you can expand, collapse or toggle your `Card` using:

``` java
    //To expand
    card.doExpand();
    
    //To collapse
    card.doCollapse();
    
    //To toggle
    card.doToogleExpand();
```

### Expand the card with a longClick

You can use a long click instead of the single click to expand/collapse cards.

You can use the method `useLongClick(true)` on your `ViewToClickToExpand`.

For example:
``` java
    ViewToClickToExpand viewToClickToExpand =
                    ViewToClickToExpand.builder()
                            .highlightView(false)
                            .useLongClick(true)
                            .setupCardElement(ViewToClickToExpand.CardElementUI.CARD);
    card.setViewToClickToExpand(viewToClickToExpand);
```

Pay attention: this feature is not enabled for the built-in expand button in Header.


Also you can use a simple:

``` java
  view.setOnLongClickListener(new OnLongClickListener(){     
           @Override
           public boolean onLongClick(View view) {
                view.doToogleExpand();
           }
  });
``` 


### CardExpand and CardListView

You can use the `CardExpand` inside the `CardListView`.

With a **`CardArrayAdapter`** you can use the same code described above.

You can find an example in [`ListExpandCardFragment`](/demo/stock/src/main/java/it/gmariotti/cardslib/demo/fragment/native/NativeListExpandCardFragment.java).

``` java
        Card card = new Card(getActivity());

        //Create a CardHeader
        CardHeader header = new CardHeader(getActivity());
        header.setButtonExpandVisible(true);
        //Add Header to card
        card.addCardHeader(header);

        //This provides a simple (and useless) expand area
        CustomExpandCard expand = new CustomExpandCard(getActivity(),i);
        //Add Expand Area to Card
        card.addCardExpand(expand);

        //Just an example to expand a card
        card.setExpanded(true);
```

If you want to set a card as expanded/collapsed, you can use:

``` java
        card.setExpanded(true);
```

If you want to know about your card, you can use

``` java

        card.isExpanded()
```

When you click a card to expand or collapse the method `adapter.notifyDataSetChanged` will be called.


With a **`CardCursorAdapter`** you have to use this code:

``` java
    public class MyCursorCardAdapter extends CardCursorAdapter {


        public MyCursorCardAdapter(Context context) {
            super(context);
        }

        @Override
        protected Card getCardFromCursor(Cursor cursor) {
            MyCursorCard card = new MyCursorCard(super.getContext());
            setCardFromCursor(card,cursor);

            //Create a CardHeader
            CardHeader header = new CardHeader(getActivity());
            //Set the header title
            header.setButtonExpandVisible(true);
            //Add Header to card
            card.addCardHeader(header);

            //This provides a simple (and useless) expand area
            CustomExpandCard expand = new CustomExpandCard(getActivity());
            //Add Expand Area to Card
            card.addCardExpand(expand);

            //Don't use card.setExpanded(true)!
            //Don't use mAdapter.setExpanded(card) here !

            //Animator listener
            card.setOnExpandAnimatorEndListener(new Card.OnExpandAnimatorEndListener() {
                @Override
                public void onExpandEnd(Card card) {
                    Toast.makeText(getActivity(),"Expand "+card.getCardHeader().getTitle(),Toast.LENGTH_SHORT).show();
                }
            });

            card.setOnCollapseAnimatorEndListener(new Card.OnCollapseAnimatorEndListener() {
                @Override
                public void onCollapseEnd(Card card) {
                    Toast.makeText(getActivity(),"Collpase " +card.getCardHeader().getTitle(),Toast.LENGTH_SHORT).show();
                }
            });

            return card;
        }
```

**Pay attention**: To work with a `CardCursorAdapter` and expand/collapse feature you have to set your Id inside your card.

``` java
   card.setId("xxxx)";
```

The `id` must be unique, and it can be useful to set it with your id in the database.


If you want to set a card as expanded/collapsed, you can use one of these methods:

``` java
        //To expand
        mAdapter.setExpanded(card);
        mAdapter.setExpanded(cardId);

        //To collapse
        mAdapter.setCollapsed(card);
        mAdapter.setCollapsed(cardId);
```

**Pay attention**: don't call this method inside `getCardFromCursor` method (because it is called by `bindView` method)


If you want to know about your card, you can use:

``` java
    .mAdapter.isExpanded(Card card)
```

If you want to know  all ids expanded you can use:

``` java
    .mAdapter.getExpandedIds()
```

In this case the method `adapter.notifyDataSetChanged` will NOT be called.


**Migration from 1.4.0 (and previous releases) - to 1.4.2**

The 1.4.2 may introduce a breaking change with CardCursorAdapter and expand feature.
To migrate your code you have to:

* call `card.setId("xxxx)";` in your `getCardFromCursor` method
* remove `card.setExpanded(true/false)` from your adapter code
* use `mAdapter.setExpanded(card);` as described above to expand/collapse your cards.


### Style

You can customize some properties with your style and drawable files.
The quickest way to start with this would be to copy the specific style or drawable in your project and
change them.

For the **CardViewNative**:

These are the main **style properties**:

* `card.native.main_contentExpand`: expand layout style
* `card.native.expand_simple_title`: title style

**margins**:

``` xml
    <dimen name="card_expand_native_layout_padding_left">12dp</dimen>
    <dimen name="card_expand_native_layout_padding_right">12dp</dimen>
    <dimen name="card_expand_native_layout_padding_top">12dp</dimen>
    <dimen name="card_expand_native_layout_padding_bottom">12dp</dimen>
```
**color properties**:

* `card_expand_title_color`: default expand text color

Example to change header title color:

```xml
    <color name="card_expand_title_color">#B3B2B2</color>
```

**Text Size** 
```xml
<dimen name="card_expand_native_simple_title_text_size">14sp</dimen>
```
 
**Text Font**
values-v16/fonts.xml
``` xml
 <string name="card_native_font_fontFamily_expand">sans-serif</string>
``` 
values-v21/fonts.xml
``` xml
 <string name="card_native_font_fontFamily_expand">sans-serif</string>
```
 
For the **CardView**:
 
These are the main **style properties**:

* `card.main_contentExpand`: expand layout style
* `card.expand_simple_title`: title style

```
**color properties**:

* `card_expand_title_color`: default expand text color

Example to change header title color:

```xml
 <color name="card_expand_title_color">#B3B2B2</color>
```

**Text Size** 
```xml
 <dimen name="card_expand_simple_title_text_size">14sp</dimen>
```
  
**Text Font**
values-v16/fonts.xml
``` xml
  <string name="card_font_fontFamily_expand">sans-serif-condensed</string>
``` 
  