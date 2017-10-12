import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs/Rx';
import { JhiEventManager } from 'ng-jhipster';

import { UserPick } from './user-pick.model';
import { UserPickService } from './user-pick.service';

@Component({
    selector: 'jhi-user-pick-detail',
    templateUrl: './user-pick-detail.component.html'
})
export class UserPickDetailComponent implements OnInit, OnDestroy {

    userPick: UserPick;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private userPickService: UserPickService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInUserPicks();
    }

    load(id) {
        this.userPickService.find(id).subscribe((userPick) => {
            this.userPick = userPick;
        });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInUserPicks() {
        this.eventSubscriber = this.eventManager.subscribe(
            'userPickListModification',
            (response) => this.load(this.userPick.id)
        );
    }
}
