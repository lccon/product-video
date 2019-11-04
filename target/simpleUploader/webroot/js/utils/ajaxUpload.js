/* created at 2018/4/12 by BlueSky @cilicili */
const ajaxUpload = {
  upload(data) {
    let json = {};
    $.ajax({
      url:API_SERVER_padEnd("/upload/"+`${utils.getCookie('apply')}`),
      data,
      type: 'POST',
      encType: 'multipart/form-data',
      processData: false,
      contentType: false,
      cache: false,
      success(response) {
        json = response;
      }
    });
    return json;
  }
};