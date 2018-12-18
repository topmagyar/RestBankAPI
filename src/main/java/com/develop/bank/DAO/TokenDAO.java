package com.develop.bank.DAO;

import com.develop.bank.model.ValidToken;

/**
 * @author Yehor Bobyk <ybobuk@tibco.com>
 */

public interface TokenDAO {
    void save(String token);
    ValidToken get(String token);
}
