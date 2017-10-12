import { BaseEntity, User } from './../../shared';

export class UserPick implements BaseEntity {
    constructor(
        public id?: number,
        public symbl?: string,
        public own?: boolean,
        public entryPrice?: number,
        public up?: boolean,
        public target?: number,
        public time?: string,
        public user?: User,
    ) {
        this.own = false;
        this.up = false;
    }
}
