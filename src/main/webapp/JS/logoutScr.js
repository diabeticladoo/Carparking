function onLoad() {
    gapi.load('auth2', function () {
        gapi.auth2.init();
    });
    window.fbAsyncInit = function () {
        FB.init({
            appId: '300478212005443',
            cookie: true,
            xfbml: true,
            version: 'v12.0'
        });
    };
}
function myFunction() {
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
        console.log('User signed out.');
    });
    FB.logout(function (response) { });
    document.logoutForm.submit();
}