<map version="freeplane 1.9.0">
<!--To view this file, download free mind mapping software Freeplane from http://freeplane.sourceforge.net -->
<node TEXT="map structure" LOCALIZED_STYLE_REF="AutomaticLayout.level.root" FOLDED="false" ID="ID_696401721" CREATED="1610381621824" MODIFIED="1633371112867">
<edge COLOR="#000000"/>
<hook NAME="MapStyle" zoom="1.21">
    <properties show_icon_for_attributes="true" fit_to_viewport="false" show_note_icons="true" edgeColorConfiguration="#808080ff,#ff0000ff,#0000ffff,#00ff00ff,#ff00ffff,#00ffffff,#7c0000ff,#00007cff,#007c00ff,#7c007cff,#007c7cff,#7c7c00ff"/>

<map_styles>
<stylenode LOCALIZED_TEXT="styles.root_node" STYLE="oval" UNIFORM_SHAPE="true" VGAP_QUANTITY="24 pt">
<font SIZE="24"/>
<edge COLOR="#000000"/>
<stylenode LOCALIZED_TEXT="styles.predefined" POSITION="right" STYLE="bubble">
<edge COLOR="#000000"/>
<stylenode LOCALIZED_TEXT="default" ID="ID_271890427" ICON_SIZE="12 pt" COLOR="#000000" STYLE="fork">
<arrowlink SHAPE="CUBIC_CURVE" COLOR="#000000" WIDTH="2" TRANSPARENCY="200" DASH="" FONT_SIZE="9" FONT_FAMILY="SansSerif" DESTINATION="ID_271890427" STARTARROW="DEFAULT" ENDARROW="NONE"/>
<font NAME="Verdana" SIZE="10" BOLD="false" ITALIC="false"/>
<richcontent CONTENT-TYPE="plain/auto" TYPE="DETAILS"/>
<richcontent TYPE="NOTE" CONTENT-TYPE="plain/auto"/>
<edge COLOR="#000000"/>
</stylenode>
<stylenode LOCALIZED_TEXT="defaultstyle.details">
<edge COLOR="#000000"/>
</stylenode>
<stylenode LOCALIZED_TEXT="defaultstyle.attributes" STYLE="fork">
<font NAME="Consolas" SIZE="10" BOLD="false" STRIKETHROUGH="false"/>
<edge COLOR="#000000"/>
</stylenode>
<stylenode LOCALIZED_TEXT="defaultstyle.note" COLOR="#000000" BACKGROUND_COLOR="#ffffff" TEXT_ALIGN="LEFT">
<edge COLOR="#000000"/>
</stylenode>
<stylenode LOCALIZED_TEXT="defaultstyle.floating">
<edge STYLE="hide_edge" COLOR="#000000"/>
<cloud COLOR="#f0f0f0" SHAPE="ROUND_RECT"/>
</stylenode>
<stylenode LOCALIZED_TEXT="defaultstyle.selection" BACKGROUND_COLOR="#ff9999" STYLE="bubble" BORDER_COLOR_LIKE_EDGE="false">
<edge COLOR="#000000"/>
</stylenode>
</stylenode>
<stylenode LOCALIZED_TEXT="styles.user-defined" POSITION="right" STYLE="bubble">
<edge COLOR="#000000"/>
<stylenode LOCALIZED_TEXT="styles.topic" COLOR="#7f7f7f" STYLE="fork">
<font NAME="Fira Code SemiBold" SIZE="14" BOLD="true"/>
<edge COLOR="#000000"/>
</stylenode>
<stylenode LOCALIZED_TEXT="styles.subtopic" COLOR="#7f7f7f" STYLE="fork">
<font NAME="Fira Code SemiBold" SIZE="12" BOLD="true"/>
<edge COLOR="#000000"/>
</stylenode>
<stylenode LOCALIZED_TEXT="styles.important" ID="ID_67550811">
<icon BUILTIN="yes"/>
<arrowlink COLOR="#003399" TRANSPARENCY="255" DESTINATION="ID_67550811"/>
<edge COLOR="#000000"/>
<font NAME="Verdana"/>
</stylenode>
<stylenode TEXT="source" ID="ID_1537999715" COLOR="#7f7f7f" STYLE="bubble">
<icon BUILTIN="emoji-26F2"/>
<font BOLD="true"/>
</stylenode>
<stylenode TEXT="polemic" COLOR="#7f7f7f" STYLE="bubble">
<icon BUILTIN="emoji-1F5E3"/>
<font BOLD="true"/>
</stylenode>
<stylenode TEXT="structuring hub" COLOR="#7f7f7f" STYLE="bubble">
<font BOLD="true" ITALIC="false"/>
</stylenode>
<stylenode TEXT="objection" COLOR="#7f7f7f" STYLE="bubble">
<icon BUILTIN="closed"/>
<font BOLD="true" ITALIC="false"/>
</stylenode>
</stylenode>
<stylenode LOCALIZED_TEXT="styles.AutomaticLayout" POSITION="right" STYLE="bubble">
<edge COLOR="#000000"/>
<stylenode LOCALIZED_TEXT="AutomaticLayout.level.root" COLOR="#ffffff" BACKGROUND_COLOR="#000000" STYLE="fork" SHAPE_HORIZONTAL_MARGIN="10 pt" SHAPE_VERTICAL_MARGIN="10 pt">
<font SIZE="10" BOLD="true"/>
</stylenode>
<stylenode LOCALIZED_TEXT="AutomaticLayout.level,1" COLOR="#7f7f7f">
<font NAME="Verdana" SIZE="16" BOLD="true"/>
<edge COLOR="#000000"/>
</stylenode>
<stylenode LOCALIZED_TEXT="AutomaticLayout.level,2" COLOR="#7f7f7f">
<font NAME="Verdana" SIZE="14" BOLD="true"/>
<edge COLOR="#000000"/>
</stylenode>
<stylenode LOCALIZED_TEXT="AutomaticLayout.level,3" COLOR="#7f7f7f">
<font NAME="Verdana" SIZE="12" BOLD="true"/>
<edge COLOR="#000000"/>
</stylenode>
<stylenode LOCALIZED_TEXT="AutomaticLayout.level,4" COLOR="#111111">
<font NAME="Verdana" SIZE="10"/>
<edge COLOR="#000000"/>
</stylenode>
</stylenode>
</stylenode>
</map_styles>
</hook>
<hook NAME="AutomaticEdgeColor" COUNTER="20" RULE="ON_BRANCH_CREATION"/>
<node TEXT="1. a topic of 1st importance" POSITION="right" ID="ID_1437858929" CREATED="1630070544392" MODIFIED="1633371120128">
<edge COLOR="#000000"/>
<node TEXT="1. the most important sub-topic" ID="ID_628279712" CREATED="1630076904659" MODIFIED="1630077948863">
<node TEXT="an observed example" ID="ID_806249186" CREATED="1630078157027" MODIFIED="1630143488750">
<node TEXT="src" STYLE_REF="source" ID="ID_23309864" CREATED="1630085886862" MODIFIED="1630087537668">
<node TEXT="name of the source&#xa;http://url_to_the_source" ID="ID_1295612875" CREATED="1630085891801" MODIFIED="1630085924332">
<node TEXT="justification" STYLE_REF="polemic" ID="ID_1183042824" CREATED="1630088423115" MODIFIED="1630088444974">
<node TEXT="argument by the &quot;name of the source&quot; in favour of &quot;an observed example&quot;" ID="ID_606706713" CREATED="1630088469733" MODIFIED="1630088589205"/>
</node>
</node>
</node>
<node TEXT="structuring hub" ID="ID_1034146747" CREATED="1630088619160" MODIFIED="1630088899645" COLOR="#7f7f7f" STYLE="bubble">
<font BOLD="true" ITALIC="false"/>
</node>
<node TEXT="objection" STYLE_REF="structuring hub" ID="ID_1746482978" CREATED="1630088926392" MODIFIED="1630089007523">
<icon BUILTIN="closed"/>
</node>
</node>
</node>
<node TEXT="2. a less important sub-topic" ID="ID_1966235475" CREATED="1630077928645" MODIFIED="1630077954373"/>
</node>
<node TEXT="2. a topic of 2nd importance" POSITION="right" ID="ID_730976074" CREATED="1630076922288" MODIFIED="1633371118827">
<edge COLOR="#000000"/>
<attribute_layout NAME_WIDTH="59.25 pt" VALUE_WIDTH="59.25 pt"/>
</node>
<node TEXT="sources" POSITION="left" ID="ID_1182517609" CREATED="1630070639841" MODIFIED="1630073150165">
<edge COLOR="#000000"/>
<node TEXT="full name of a source" ID="ID_894009343" CREATED="1630070644557" MODIFIED="1633371111492">
<edge COLOR="#000000"/>
</node>
</node>
</node>
</map>
