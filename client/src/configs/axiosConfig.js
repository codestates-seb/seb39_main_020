import axios from "axios";

const axiosConfig = {};

const aget = (url, data, successFn, failFn, newProp) => {
  return axios({
    ...axiosConfig,
    url,
    method: "get",
    params: data,
    ...newProp,
  })
    .then((res) => {
      !!successFn && successFn(res);
      return res.data;
    })
    .catch((err) => {
      !!failFn && failFn();
      return err;
    });
};

const apost = (url, data, successFn, failFn, newProp) => {
  return axios({
    ...axiosConfig,
    url,
    method: "post",
    data,
    ...newProp,
  })
    .then((res) => {
      !!successFn && successFn(res);
      return res.data;
    })
    .catch((err) => {
      !!failFn && failFn();
      return err;
    });
};

const aput = (url, data, successFn, failFn, newProp) => {
  return axios({
    ...axiosConfig,
    url,
    method: "put",
    data,
    newProp,
  })
    .then((res) => {
      console.log(res)(!!successFn) && successFn(res);
      return res.data;
    })
    .catch((err) => {
      !!failFn && failFn();
      return err;
    });
};

const adelete = (url, data, successFn, failFn, newProp) => {
  return axios({
    ...axiosConfig,
    url,
    method: "delete",
    data,
    newProp,
  })
    .then((res) => {
      !!successFn && successFn();
      return res.data;
    })
    .catch((err) => {
      !!failFn && failFn();
      return err;
    });
};

export { aget, apost, aput, adelete };
