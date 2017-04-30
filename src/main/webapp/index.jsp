<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%-- root URL --%>
<c:set var='rootUrl' value='${pageContext.request.contextPath}' />
<c:set var='resource' value='${rootUrl}/Resource/' />

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Fight Fires</title>
    <link rel="stylesheet" href="${resource}css/demo.css">
    <script type="text/javascript" src="${resource}js/jquery.min.js"></script>
    <script type="text/javascript" src="${resource}js/papaparse.min.js"></script>
    <script type="text/javascript" src="${resource}js/demo.js"></script>
  </head>
  <script>
    setBasicHref('${pageContext.request.contextPath}');
  </script>
  <body>
    <div id="map"></div>
    <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=&libraries=visualization&callback=initMap"></script>
  </body>
</html>