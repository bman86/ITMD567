import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager, JhiParseLinks, JhiPaginationUtil, JhiAlertService } from 'ng-jhipster';

import { UserPick } from './user-pick.model';
import { UserPickService } from './user-pick.service';
import { ITEMS_PER_PAGE, Principal, ResponseWrapper } from '../../shared';
import { PaginationConfig } from '../../blocks/config/uib-pagination.config';

@Component({
    selector: 'jhi-user-pick',
    templateUrl: './user-pick.component.html'
})
export class UserPickComponent implements OnInit, OnDestroy {
userPicks: UserPick[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private userPickService: UserPickService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {
    }

    loadAll() {
        this.userPickService.query().subscribe(
            (res: ResponseWrapper) => {
                this.userPicks = res.json;
            },
            (res: ResponseWrapper) => this.onError(res.json)
        );
    }
    ngOnInit() {
        this.loadAll();
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInUserPicks();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: UserPick) {
        return item.id;
    }
    registerChangeInUserPicks() {
        this.eventSubscriber = this.eventManager.subscribe('userPickListModification', (response) => this.loadAll());
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }
}
