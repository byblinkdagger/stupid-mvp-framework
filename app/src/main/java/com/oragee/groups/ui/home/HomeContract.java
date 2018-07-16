package com.oragee.groups.ui.home;

import com.oragee.groups.base.IFV;
import com.oragee.groups.base.IM;
import com.oragee.groups.base.IP;

/**
 * Created by lucky on 2017/12/14.
 * Here be dragons
 * 前方高能
 */

public class HomeContract {

    interface V extends IFV {
        //set ui
    }

    interface P extends IP {
    }

    interface M extends IM {
        //call net request to get observable
    }

}
