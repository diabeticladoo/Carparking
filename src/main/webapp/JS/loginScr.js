function onSignIn(googleUser) {
    var profile = googleUser.getBasicProfile();
    document.FormNonCr.loginw.value = "G";
    document.FormNonCr.fname.value = profile.getGivenName();
    document.FormNonCr.lname.value = profile.getFamilyName();
    document.FormNonCr.email.value = profile.getEmail();
    document.FormNonCr.submit();
}

function statusChangeCallback(response) {
    if (response.status === 'connected') {
        testAPI();
    }
}


function checkLoginState() {
    FB.getLoginStatus(function (response) {
        statusChangeCallback(response);
    });
}


window.fbAsyncInit = function () {
    FB.init({
        appId: '300478212005443',
        cookie: true,
        xfbml: true,
        version: 'v12.0'
    });


    FB.getLoginStatus(function (response) {
        statusChangeCallback(response);
    });
};



function testAPI() {
    FB.api('/me/?fields=id,first_name,last_name,email', function (response) {
        document.FormNonCr.loginw.value = "FB";
        document.FormNonCr.fname.value = response.first_name;
        document.FormNonCr.lname.value = response.last_name;
        document.FormNonCr.email.value = response.email;
        document.FormNonCr.submit();
    });
}