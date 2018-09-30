<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:template match="/">
        <table border="1">
            <tr bgcolor="#9acd32">
                <th>№</th>
                <th>Name</th>
                <th>Position</th>
                <th>Department</th>
                <th>Salary</th>
            </tr>
            <xsl:for-each select="employee">
                <tr>
                    <td><xsl:value-of select="@empno"/></td>
                    <td><xsl:value-of select="ename"/></td>
                    <td><xsl:value-of select="job"/></td>
                    <td><xsl:value-of select="department/name"/></td>
                    <td><xsl:value-of select="@sal"/></td>
                </tr>
            </xsl:for-each>
        </table>
    </xsl:template>
</xsl:stylesheet>