import client from './client';

export const getLoginCheck= ({ userId,userPassword }) => {
  return client.get(`/api/member/register/${userId}`);
};
