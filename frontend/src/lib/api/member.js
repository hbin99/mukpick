import client from './client';

export const getFindId1 = ({ userId }) => {
  return client.get(`/api/member/register/${userId}`);
};
export const getFindId = ({ userName, phone, email }) => {
  return client.post(`/api/member/user-find`,{
    userName :userName,
    phone : phone,
    email : email
  });
};
export const register =
    ({ userId, userName,userPassword,email,phone,gender,age  }) => {
  return client.post(`/api/member/register`,{
    userId :userId,
    userName :userName,
    userPassword : userPassword,
    email : email,
    phone : phone,
    gender : gender,
    age : age,
  });
};
