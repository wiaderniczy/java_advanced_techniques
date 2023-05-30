<?xml version="1.0" encoding="utf-8"?>
<html xsl:version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<body style="font-size:12pt;backround-color:#808080">
		<h1 style="font-size:20pt;color:#FF0000">Hello</h1>
		<xsl:for-each select="channel/item">
			<ul>
				<li>
					<xsl:value-of select="title"/>
					<xsl:value-of select="description"/>
					<xsl:value-of select="pubDate"/>
					<xsl:value-of select="baseCurrency"/>
					<xsl:value-of select="targetCurrency"/>
					<xsl:value-of select="exchangeRate"/>
					<xsl:value-of select="inverseRate"/>
				</li>
			</ul>
		<xsl:for-each>
	</body>
</html>