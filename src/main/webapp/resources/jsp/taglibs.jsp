<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
    String path = request.getContextPath();
    String basePath;
    if (request.getServerPort() == 80) {
        basePath = request.getScheme() + "://"
                + request.getServerName() + path;
    } else {
        basePath = request.getScheme() + "://"
                + request.getServerName() + ":"
                + request.getServerPort() + path;
    }
%>


