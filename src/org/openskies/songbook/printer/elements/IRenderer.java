package org.openskies.songbook.printer.elements;

import org.openskies.songbook.printer.parser.RenderMode;

/**
 * The Interface IRenderer.
 */
public interface IRenderer {

	/**
	 * Render.
	 *
	 * @return the string
	 */
	public String render(RenderMode mode);
	
	// Test at https://jsfiddle.net/0adkhxe7/117/
	
	// HTML
	/*
	<div id="title">I can only imagine (A131)</div>
	<div id="artist">Bart Millard</div>

	1. I can only im<span class="chord"><span class="inner">D</span></span>agine what it will be like when I <span class="chord"><span class="inner">G</span></span>walk by Your side.<br>
	I can only  <span class="chord"><span class="inner">D</span></span>imagine what my eyes will see when Your <span class="chord"><span class="inner">G</span></span>face is before me.<br>
	I can only im<span class="chord"><span class="inner">D</span></span>agine, I can only i<span class="chord"><span class="inner">G</span></span>magine.
	<div class="chorus"><div class="chorus-text">
	Sur[Gsus2]rounded by Your glory, what [Asus4]will my heart feel? 
	 Will I [D]dance for You, Jesus, or in awe of You be still? 
	 Will I [Gsus2]stand in Your presence or to my [Asus4]knees will I fall? 
	 Will I [D]sing Hallelujah? Will I be [Asus4]able to speak at all? 
	 I can only [Gsus2]imagine,[Asus4]I can only im[D]agine. 
	</div></div>

	2. <span class="chord"><span class="inner">E</span></span>I can only imagine when that <span class="chord"><span class="inner">Asus2</span></span>day comes,
	When I <span class="chord"><span class="inner">Asus2</span></span>find myself standing <span class="chord"><span class="inner">H</span></span>in the sun,
	I can only <span class="chord"><span class="inner">E</span></span>imagine when all <span class="chord"><span class="inner">Esus4</span></span>I will do
	Is for<span class="chord"><span class="inner">Asus2</span></span>ever, forever <span class="chord"><span class="inner">H</span></span>worship You.
	I can only <span class="chord"><span class="inner">E</span></span>imagine. I can only <span class="chord"><span class="inner">Asus2</span></span>imagine<span class="chord"><span class="inner">H</span></span>.

	<div id="ccli">2978857</div>
	<div id="copyright">1999 MercyMe Music</div>
	
	*/
	
	// CSS
	/*
	* {
		  font-family: "Century Gothic",CenturyGothic,AppleGothic,sans-serif;
		  font-size: var(--textSize);
		  line-height: 200%;
		}

		#title {
		  font-size: large;
		  font-weight: bold;
		  padding-bottom: 0px;
		}

		.chorus-text {
		  border-style: solid;
		  border-width: thin;
		  border-color: #FF0000;
		   padding: 5px;
		}

		.chorus {
		  padding-left: 20px;
		  padding-top: 10px;
		  padding-bottom: 20px;
		}

		#ccli::before {
		  content: "CCLI: ";
		}

		#copyright::before {
		  content: "\00a9 ";
		}

		#copyright {
		  font-size: x-small;
		  line-height:1.2 !important
		}

		#ccli {
		  font-size: x-small;
		  line-height:1.2 !important
		}

		#artist {
		  font-size: small;
		  padding-bottom: 10px;
		}

		.chord { position: absolute;
		  font-size: 13px;
		  font-weight: bold;
		  color: red;
		}

		.chord .inner {
		  position: relative;
		  top: -1em;
		}
		*/
}
