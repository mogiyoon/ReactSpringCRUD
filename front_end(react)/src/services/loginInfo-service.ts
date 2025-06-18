import axios from 'axios';
import { LoginInfo } from '@/model/loginInfo-model';

export const fetchLogin = async (newLoginInfo: LoginInfo): Promise<{token: string}> => {
    const response = await axios.post<{token: string}>(
      'http://localhost:8080/api/login', newLoginInfo
    );
    console.log(response);
    return response.data;
};