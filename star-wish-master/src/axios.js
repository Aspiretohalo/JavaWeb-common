import axios from "axios";
// import router from "./router";


axios.defaults.baseURL = 'https://localhost:8085';
// axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
axios.defaults.headers.common['Authorization'] = `Bearer ` + localStorage.getItem('token');

export default axios