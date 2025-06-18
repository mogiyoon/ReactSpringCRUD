import axios from 'axios';
import { Userinfo } from '@/model/userinfo-model';

export const fetchCreateUserinfo = async (newUserinfo: Userinfo): Promise<Userinfo> => {
  const response = await axios.post<Userinfo>('http://localhost:8080/api/userinfo', newUserinfo);
  return response.data;
};