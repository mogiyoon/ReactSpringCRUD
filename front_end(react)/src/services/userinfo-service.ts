import axios from 'axios';
import { Userinfo } from '@/model/userinfo-model';

export const fetchGetUserinfo = async (): Promise<Userinfo[]> => {
  const response = await axios.get<Userinfo[]>('http://localhost:8080/userinfo');
  return response.data
}

export const fetchCreateUserinfo = async (newUserinfo: Userinfo): Promise<Userinfo> => {
  const response = await axios.post<Userinfo>('http://localhost:8080/userinfo', newUserinfo);
  return response.data;
};