<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<%-- root URL --%>
<c:set var='rootUrl' value='${pageContext.request.contextPath}' />
<c:set var='resource' value='${rootUrl}/Resource/' />

<html>
  <head>
    <link rel="stylesheet" href="${resource}css/demo.css">
    <script type="text/javascript" src="${resource}js/jquery.min.js"></script>
    <script type="text/javascript" src="${resource}js/demo.js"></script>
  </head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Fight Fires</title>
  <body>
    <div id="map"></div>
    <!-- <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBb2UY3-iQUxup6OFXSJUGV3WZN_GgoMMk&callback=initMap" async defer></script> -->
    <script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBb2UY3-iQUxup6OFXSJUGV3WZN_GgoMMk&libraries=visualization&callback=initMap"></script>
  </body>
</html>