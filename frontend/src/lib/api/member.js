import client from './client';

export const getFindId = ({ userName, phone, email }) => {
  return client.post(`/api/member/user-find`,{
    userName :userName,
    phone : phone,
    email : email
  });
};
export const getFindPassword = ({ userId, phone, email }) => {
  return client.post(`/api/member/password-find-mail`,{
    userId :userId,
    phone : phone,
    email : email
  });
};
export const register =
    ({ userId, userName,userPassword,email,phone,gender,age  }) => {
    console.log(userId);
  return client.post(`/api/member/register`,{
    userId :userId,
    userName :userName,
    password : userPassword,
    email : email,
    phone : phone,
    gender : gender,
    age : age,
  });
};
export const getCheckId = ({ userId }) => {
  console.log(userId);
  return client.get(`/api/member/register/${userId}`);
}


