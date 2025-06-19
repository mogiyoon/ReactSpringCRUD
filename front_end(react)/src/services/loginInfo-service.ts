import axios from 'axios';
import { LoginInfo } from '@/model/loginInfo-model';

export const fetchLogin = async (newLoginInfo: LoginInfo): Promise<string> => {
  const response = await axios.post<string>(
    'http://localhost:8080/api/login', newLoginInfo, {withCredentials: true}
  );
  return response.data;
};

export const fetchLogout = async (): Promise<string> => {
  const response = await axios.post<string>(
    'http://localhost:8080/api/logout', {withCredentials: true}
  );
  return response.data;
};