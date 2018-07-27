package com.hd.accountBusinessContactsmapper;

import java.util.List;
import com.hd.beans.Contacts;

public interface ContactsMapper {
	/**
	  * 新增联系人
	  * @param contacts
	  * @return
	  * throws Exception
	  */
    public int insertContacts(Contacts contacts) throws Exception;
    
    /**
     * 修改联系人
     * @param contacts
     * @param con_id
     * @return
     * @throws Exception
     * 
     */
    public int updateContacts(Contacts contacts,int con_id) throws Exception;
    
    /**
     * 删除联系人
     * @param contacts
     * @param con_id
     * @return 
     * @throws Exception
     */
    public int deleteContacts(int con_id)throws Exception;
    
    /**
     * 根据con_id查询联系人信息
     * @param con_id
     * @return
     * @throws Exception
     */
    public Contacts selectContactsById(int con_id)throws Exception;
    
    /**
     * 查询所有的联系人信息
     * 
     */
    public List<Contacts> selectAllContacts() throws Exception;
}
