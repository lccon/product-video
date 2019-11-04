/* created at 2018/4/9 by BlueSky @cilicili */
const ajaxUser = {
  findUsername(username) {
    let json = {};
    $.get(
      API_SERVER_padEnd("user/findUsername"), {
        username
        , token: utils.getCookie("token")
      }, response => {
        json = response;
      });
    return json;
  }, add({username, password}) {
    let json = {};
    $.post(
      API_SERVER_padEnd("/user/add"), {
        username, password
      }, response => {
        json = response;
      }
      );
    return json;
  }, update({username, password}) {
    let json = {};
    $.post(
      API_SERVER_padEnd("user/update"), {
        username, password, token: utils.getCookie("token"), apply: utils.getCookie("apply")
      }, response => {
        json = response;
      });
    return json;
  }
};