/* created at 2018/4/10 by BlueSky @cilicili */

let LOGIN_STATUS = ajaxLogin.now();

const UPDATE_LOGIN_STATUS = () => {
  LOGIN_STATUS = ajaxLogin.now();
};

// if (LOGIN_STATUS.status === false && utils.getPage() !== 'index' && utils.getPage() !== 'login') {
//   window.location = 'index.htm';
// }

const VIDEO_INFO = ((id) => {
  if (id === null) {
    return {
      status: "failure"
    };
  } else {
    const video = ajaxVideo.findId(id);
    if (video != null && video.uploadTime != null) {
      video.uploadTime = utils.calcTime(video.uploadTime.dateTime);
    }
    return video;
  }
})(utils.getRequest("id"));